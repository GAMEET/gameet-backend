package gameet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gameet.entity.JuegosUsuario;
import gameet.entity.Usuario;

@Service
public class SistemaRecomendacionService {

	private List<Usuario> usuarios;
	
	@Autowired
	private UsuarioService usuarioServ;
	
	@Autowired
	private JuegoService juegosServ;

	public SistemaRecomendacionService(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

    //Metodo principal para recomendar usuarios al usuario autenticado
	public List<Usuario> recomendarUsuarios(Usuario usuarioOrigen) {
		usuarios= usuarioServ.getAllUsersActivosExcept(usuarioOrigen.getUsername());
		
	    // Paso 1: Eliminamos usuarios inactivos y al usuario autenticado
	    List<Usuario> paso1 = usuarios.stream()
	            .filter(u -> u.isActivo() && !u.getUsername().equals(usuarioOrigen.getUsername()))
	            .collect(Collectors.toList());
	    
	    // Paso 2: Eliminamos usuarios ya rechazados anteriormente
	    List<Usuario> paso2 = paso1.stream()
	            .filter(u -> !usuarioOrigen.getRechazados().contains(u.getUsername()))
	            .collect(Collectors.toList());

	    // Paso 3: Eliminamos usuarios que no tengan juegos en comun ni consolas
	    List<Usuario> paso3 = paso2.stream()
	            .filter(u -> tienenJuegoComun(usuarioOrigen, u))
	            .collect(Collectors.toList());
	    
	    // Paso 4: Ordenamos los usuarios por puntuación descendentemente
	    List<Usuario> paso4 = paso3.stream()
	            .sorted((u1, u2) -> {
	                double score1 = calcularPuntuacion(usuarioOrigen, u1);
	                double score2 = calcularPuntuacion(usuarioOrigen, u2);
	                return Double.compare(score2, score1);
	            })
	            .collect(Collectors.toList());
	    
	    // Devolver lista de usuarios recomendados
	    return paso4;
	}


    //Metodo para comprobar si dos usuarios tienen al menos un juego y consola en común
    private boolean tienenJuegoComun(Usuario usuarioAutenticado, Usuario usuarioComparado) {
    	
    	List<JuegosUsuario> juegosusuarioAutenticado = juegosServ.obtenerJuegosUsuarios(usuarioAutenticado.getJuegos());
    	List<JuegosUsuario> juegosusuarioComparado = juegosServ.obtenerJuegosUsuarios(usuarioComparado.getJuegos());
    	
        for (JuegosUsuario ju1 : juegosusuarioAutenticado) {
            for (JuegosUsuario ju2 : juegosusuarioComparado) {
                if (ju1.getJuego().equals(ju2.getJuego()) && ju1.getConsola().equals(ju2.getConsola())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Metodo para calcular la similitud (puntuacion) entre dos usuarios
	private double calcularPuntuacion(Usuario usuarioAutenticado, Usuario usuarioComparado) {
		
    	List<JuegosUsuario> juegosusuarioAutenticado = juegosServ.obtenerJuegosUsuarios(usuarioAutenticado.getJuegos());
    	List<JuegosUsuario> juegosusuarioComparado = juegosServ.obtenerJuegosUsuarios(usuarioComparado.getJuegos());
		
		double puntuacion = 0;

		// Características comunes
		long caracteristicasComunes = usuarioAutenticado.getCaracteristicas().stream().filter(usuarioComparado.getCaracteristicas()::contains)
				.count();
		puntuacion += caracteristicasComunes;

		// Horario comun
		if (usuarioAutenticado.getHorarioJuego().equals(usuarioComparado.getHorarioJuego())) {
			puntuacion += 1 * 0.9;
		}

		// Consolas comunes
		long consolasComunes = juegosusuarioAutenticado.stream()
				.filter(ju -> juegosusuarioComparado.stream().anyMatch(j -> j.getConsola().equals(ju.getConsola())))
				.count();
		puntuacion += consolasComunes * 0.2;

		// Juegos comunes y diferencia de niveles
		for (JuegosUsuario ju1: juegosusuarioAutenticado) {
			for (JuegosUsuario ju2 : juegosusuarioComparado) {
				if (ju1.getJuego().equals(ju2.getJuego()) && ju1.getConsola().equals(ju2.getConsola())) {
					int diferenciaNivel = Math.abs(ju1.getNivel() - ju2.getNivel());
					puntuacion += (5 - diferenciaNivel) * 0.8; // A menor diferencia de nivel, mayor puntuación
				}
			}
		}

		return puntuacion;
	}

}
