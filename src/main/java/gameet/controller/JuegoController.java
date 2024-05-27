package gameet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import gameet.entity.Juego;
import gameet.entity.JuegoRequest;
import gameet.entity.JuegosUsuario;
import gameet.entity.JuegosUsuarioRequest;
import gameet.entity.Usuario;
import gameet.service.JuegoService;
import gameet.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JuegoController {

	@Autowired
	private UsuarioService usuarioServ;

	@Autowired
	private JuegoService juegosServ;
	
	// Metodo para asignar juegos a un usuario
	@PostMapping("/api/seleccionJuego")
	public void seleccionJuego2(@RequestBody JuegosUsuarioRequest juegos, HttpServletRequest request) {

	    String username = (String) request.getAttribute("username");

	    try {
	        Usuario usuario = usuarioServ.getUsuarioByUsername(username);
	        List<String> listaJuegosUsuariosAux = usuario.getJuegos();
	        Boolean esUnico = true;
	        
	        if(!listaJuegosUsuariosAux.isEmpty() && listaJuegosUsuariosAux != null ) {
		        List<JuegosUsuario> listaJuegosUsuarios = juegosServ.getJuegosUsuarioByIds(listaJuegosUsuariosAux);
		        
		        for (JuegosUsuario ju : listaJuegosUsuarios) {
		            String juegoUsuario = ju.getJuego();
		            String consolaUsuario = ju.getConsola();

		            if (juegoUsuario.equals(juegos.getJuego()) && consolaUsuario.equals(juegos.getConsola())) {
		                ju.setNivel(juegos.getNivel());
		                juegosServ.guardarJuegoUsuario(ju);
		                esUnico=false;
		                break;
		            }
		        }
	        }
	        
	        if (esUnico) {

				String juegosusuarios = crearJuegoUsuario(usuario.getUsername(), juegos);
				List<String> listaJuegos = usuario.getJuegos();
				listaJuegos.add(juegosusuarios);
				usuario.setJuegos(listaJuegos);
				usuarioServ.guardarUsuario(usuario);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	// Metodo para eliminar la asignacion de un juega en un usuario
	@PostMapping("/api/eliminarJuego")
	public void eliminarJuego(@RequestBody JuegosUsuarioRequest juegos, HttpServletRequest request) {

		String username = (String) request.getAttribute("username");

		try {
			Usuario usuario = usuarioServ.getUsuarioByUsername(username);
			List<String> listaJuegos = usuario.getJuegos();
			JuegosUsuario juego1 = juegosServ.getJuegoUsuarioByTituloandUsusarioandConsola(juegos.getJuego(), username,
					juegos.getConsola());
			listaJuegos.remove(juego1.getId());
			usuario.setJuegos(listaJuegos);
			usuarioServ.guardarUsuario(usuario);
			eliminarJuegoUsuario(juego1.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Metodo para obtener una lista de juegos enviando el usuario autenticado en el
	// token
	@GetMapping("/api/juegosUsuario")
	public List<JuegoRequest> juegosUsuario(HttpServletRequest request) {

		String username = (String) request.getAttribute("username");
		List<JuegoRequest> res = new ArrayList<>();
		try {
			Usuario usuario = usuarioServ.getUsuarioByUsername(username);
			List<JuegosUsuario> juegosusuario = juegosServ.obtenerJuegosUsuarios(usuario.getJuegos());
			Map<String, JuegoRequest> juegosMap = new HashMap<>();

			for (JuegosUsuario ju : juegosusuario) {
				String juegoId = ju.getJuego().trim();
				Juego juego1 = juegosServ.getJuegoById(juegoId);

				if (juegosMap.containsKey(juegoId)) {
					JuegoRequest jr = juegosMap.get(juegoId);
					Integer nivelesAux = jr.getConsolaNivel().get(ju.getConsola());

					if (nivelesAux == null) {
						jr.getConsolaNivel().put(ju.getConsola(), ju.getNivel());
					}

				} else {
					Map<String, Integer> consolaNivel = new HashMap<>();
					consolaNivel.put(ju.getConsola(), ju.getNivel());
					JuegoRequest jr = new JuegoRequest(juego1, consolaNivel);
					juegosMap.put(juegoId, jr);
				}
			}

			res.addAll(juegosMap.values());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String crearJuegoUsuario(String usuario, JuegosUsuarioRequest juegos) {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		JuegosUsuario ju = new JuegosUsuario();
		ju.setConsola(juegos.getConsola());
		ju.setNivel(juegos.getNivel());
		ju.setUsuario(usuario);
		ju.setJuego(juegos.getJuego());

		DocumentReference docRef = dbFirestore.collection("juegosUsuario").document();

		try {
			docRef.set(ju);
			return docRef.getId();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public void eliminarJuegoUsuario(String documentId) {

		Firestore dbFirestore = FirestoreClient.getFirestore();

		try {
			dbFirestore.collection("juegosUsuario").document(documentId).delete().get();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Metodo para asignar juegos a un usuario
	@GetMapping("/api/juegos")
	public List<Juego> listadoJuego(HttpServletRequest request) {

		try {
			return juegosServ.getAllJuegos();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
