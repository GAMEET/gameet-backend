package gameet.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gameet.entity.JuegosUsuario;
import gameet.entity.Usuario;

@Service
public class SistemaRecomendacionService {
	
	@Autowired
	private UsuarioService usuarioServ;
	
	@Autowired
	private JuegoService juegosServ;
	
	@Autowired
	private EnlaceService enlaceServ;

    //Metodo principal para recomendar usuarios al usuario autenticado
	public List<Usuario> recomendarUsuarios(Usuario usuarioOrigen) {
		Set<String> rechazados = new HashSet<>(usuarioOrigen.getRechazados());
		String origenUsername = usuarioOrigen.getUsername();

		return usuarioServ

				// Paso 1: Eliminamos usuarios inactivos y al usuario autenticado
				.getAllUsersActivosExcept(origenUsername).stream()

				// Paso 2: Eliminamos usuarios ya rechazados anteriormente
				.filter(u -> u.isActivo() && !rechazados.contains(u.getUsername())
						&& !u.getUsername().equals(origenUsername))
				
				// Paso 3: Eliminamos usuarios que no tengan juegos en comun ni consolas
				.filter(u -> tienenJuegoComun(usuarioOrigen, u))
				
				// Paso 5: Verificar que no exista un enlace entre los usuarios de la lista de
				// recomendados y el UsuarioOrigen
				.filter(u -> !existeEnlaceEntre(usuarioOrigen, u))
				
				// Paso 4: Ordenamos los usuarios por puntuación descendentemente
				.sorted((u1, u2) -> Double.compare(calcularPuntuacion(usuarioOrigen, u2),
						calcularPuntuacion(usuarioOrigen, u1)))
				
				.collect(Collectors.toList());
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
    
    private boolean existeEnlaceEntre(Usuario usuarioOrigen, Usuario usuario) {
        return enlaceServ.existeEnlaceEntre(usuarioOrigen.getUsername(), usuario.getUsername());
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
