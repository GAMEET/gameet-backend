package gameet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.security.JwtUtil;
import gameet.service.UsuarioService;

@RestController
@RequestMapping("/auth")
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
}