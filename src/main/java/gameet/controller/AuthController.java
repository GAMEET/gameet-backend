package gameet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.security.JwtUtil;
import gameet.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario user) {

    	Usuario usuarioDB = usuarioService.getUsuarioByUsername(user.getUsername());
    	if(usuarioDB != null) {
            if (user.getUsername().equals(usuarioDB.getUsername()) && user.getPassword().equals(usuarioDB.getPassword())) {
                String token = JwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(token);
            }
    	}

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
    
    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestParam(required = true) String username,
                            @RequestParam(required = true) String password,
                            @RequestParam(required = true) String descripcion,
                            @RequestParam(required = true) String email,
                            @RequestParam(required = true) String imagenPerfil,
                            @RequestParam(required = true) String telefono,
                            @RequestParam(required = true) List<String> caracteristicas,
                            @RequestParam(required = true) String horarioJuego)
                            		throws InterruptedException, ExecutionException {

    	String error = null;
        // Verificamos que ningun parámetro es nulo
        if (username == null || password == null || descripcion == null || email == null || imagenPerfil == null
                || telefono == null || caracteristicas == null || horarioJuego == null) {
            error = "Faltan campos";
        }

        // Verificamos que el username tiene más de 4 letras
        if (username.length() <= 4) {
        	 error = "Username incorrecto";
        }

        // Verificamos que la contraseña tiene al menos 8 caracteres
        if (password.length() < 8) {
        	error = "Password incorrecto";
        }

        //Verificamos que el username es unico
        if (!esUsernameUnico(username)) {
        	error = "Username repetido";
        }
        
        // Verificamos que el correo electrónico tenga el formato adecuado
        if (!isValidEmail(email)) {
        	error = "Email inválido";
        }
        
        // Verificamos que el número de teléfono tenga 9 cifras
        if (telefono.length() != 9) {
        	error = "Teléfono inválido";
        }
        List<String> listaJuegosUsuariosvacia= new ArrayList<>();
        List<String> listausuariosvacia= new ArrayList<>();
        
        Usuario usuario = new Usuario(descripcion, email, imagenPerfil, password, Integer.valueOf(telefono),
    			username, caracteristicas, horarioJuego,true, listaJuegosUsuariosvacia, listausuariosvacia);
        usuarioService.guardarUsuario(usuario);
        String token = JwtUtil.generateToken(usuario.getUsername());
        
        if(error != null) {
        	return ResponseEntity.status(401).body(error);
        }
        return ResponseEntity.ok(token);
    }
    
    // Método de ejemplo para verificar si el username es único en la base de datos
    private boolean esUsernameUnico(String username) {
    	try {
			Usuario usuario = usuarioService.getUsuarioByUsername(username);
			if(usuario != null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
    
    // Método para verificar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}