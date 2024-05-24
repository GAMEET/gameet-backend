package gameet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

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
    public ResponseEntity<?> getUsuarioPorUsername(@RequestParam (required = true)String username) {
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
    
    @DeleteMapping("/eliminar/{username}")
    public String eliminarUsuario(@PathVariable String username) {
    	try {
			usuarioService.eliminarUsuario(username);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        return "Usuario eliminado con éxito.";
    }
    
    @PostMapping("/activar/{username}")
    public String activarUsuario(@PathVariable String username) {
    	try {
			usuarioService.activacionUsuario(username, true);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        return "Usuario activado con éxito.";
    }
    
    @PostMapping("/desactivar/{username}")
    public String desactivarUsuario(@PathVariable String username) {
    	try {
			usuarioService.activacionUsuario(username, false);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        return "Usuario desactivado con éxito.";
    }
    
}
