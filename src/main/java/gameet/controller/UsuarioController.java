package gameet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Juego;
import gameet.entity.JuegosUsuario;
import gameet.entity.Usuario;
import gameet.entity.UsuarioJuegoRequest;
import gameet.entity.UsuarioPerfilRequest;
import gameet.service.JuegoService;
import gameet.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	JuegoService juegosServ;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/getUsuarioByEmail")
	public Usuario getEmail(@RequestParam String email) throws InterruptedException, ExecutionException {
		return usuarioService.getUsuarioByEmail(email);
	}

	@GetMapping("/getUsuarioByUsername")
	public Usuario getUsername(@RequestParam String username) throws InterruptedException, ExecutionException {
		return usuarioService.getUsuarioByUsername(username);
	}

	@GetMapping("/listaUsuariosActivos")
	public ResponseEntity<List<Usuario>> listaUsuarios(@RequestParam(required = true) String username) {
		try {
			List<Usuario> usuarios = usuarioService.getAllUsersActivosExcept(username);
			return ResponseEntity.ok().body(usuarios);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/usuario")
	public ResponseEntity<?> getUsuarioPorUsername(@RequestParam(required = true) String username) {
		Usuario usuario = null;
		try {
			usuario = usuarioService.getUsuarioByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Usuario no encontrado");
		}
	}

	@DeleteMapping("/api/perfil/eliminar")
	public String eliminarUsuario(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		try {
			usuarioService.eliminarUsuario(username);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "Usuario eliminado con éxito.";
	}

	@PostMapping("/api/perfil/activar")
	public String activarUsuario(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		try {
			usuarioService.activacionUsuario(username, true);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "Usuario activado con éxito.";
	}

	@PostMapping("/api/perfil/desactivar")
	public String desactivarUsuario(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		try {
			usuarioService.activacionUsuario(username, false);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "Usuario desactivado con éxito.";
	}

	@GetMapping("/api/perfil")
	public UsuarioJuegoRequest perfilUsuario(HttpServletRequest request) {
	    String username = (String) request.getAttribute("username");
	    Usuario usuarioBase = usuarioService.getUsuarioByUsername(username);
	    
	    List<JuegosUsuario> listaAux = juegosServ.getJuegosUsuarioByIds(usuarioBase.getJuegos());
	    List<String> listaIdJuegos = listaAux.stream().map(x -> x.getJuego()).collect(Collectors.toList());
	    
	    List<Juego> listaJuegos = juegosServ.getJuegosByIds(listaIdJuegos);
	    Set<String> setTituloJuegos = listaJuegos.stream().map(x -> x.getTitulo()).collect(Collectors.toSet());
	    
	    List<String> listaTituloJuegos = new ArrayList<>(setTituloJuegos);
	    
	    UsuarioJuegoRequest res = new UsuarioJuegoRequest(usuarioBase, listaTituloJuegos);
	    return res;
	}

	@PostMapping("/api/perfil/editar")
	public Usuario actualizarUsuario(@RequestBody UsuarioPerfilRequest usuarioPerfil, HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		Usuario usuarioBase = usuarioService.getUsuarioByUsername(username);
		usuarioBase.setCaracteristicas(usuarioPerfil.getCaracteristicas());
		usuarioBase.setEmail(usuarioPerfil.getEmail());
		usuarioBase.setDescripcion(usuarioPerfil.getDescripcion());
		usuarioBase.setHorarioJuego(usuarioPerfil.getHorarioJuego());
		usuarioBase.setImagenPerfil(usuarioPerfil.getImagenPerfil());
		usuarioBase.setPassword(usuarioPerfil.getPassword());
		usuarioBase.setTelefono(usuarioPerfil.getTelefono());
		usuarioService.guardarUsuario(usuarioBase);
		return usuarioBase;
	}

}
