package gameet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.service.SistemaRecomendacionService;
import gameet.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SistemaRecomendacionController {

    @Autowired
    private SistemaRecomendacionService sistemaRecomendacionServ;
    
    @Autowired
    private UsuarioService usuarioServ;

    @GetMapping("/usuariosCompatibles/{username}")
    public List<Usuario> getRecomendaciones(@PathVariable String username) {
        Usuario usuarioBase;
		try {
			usuarioBase = usuarioServ.getUsuarioByUsername(username);
			return sistemaRecomendacionServ.recomendarUsuarios(usuarioBase);
			
		} catch (Exception e) {
			e.printStackTrace();
             throw new IllegalArgumentException("Usuario no encontrado");
		} 
    }

    @PostMapping("/usuariosCompatibles/{username}/rechazar/{rechazadoUsername}")
    public void rechazarUsuario(@PathVariable String username, @PathVariable String usernameRechazado){
        
    	try {
	    	Usuario usuarioBase = usuarioServ.getUsuarioByUsername(username);
	        usuarioBase.rechazarUsuario(username);
    	} catch (Exception e) {
			e.printStackTrace();
             throw new IllegalArgumentException("Usuario no encontrado");
		} 
    }
}
