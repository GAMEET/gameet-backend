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
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SistemaRecomendacionController {

    @Autowired
    private SistemaRecomendacionService sistemaRecomendacionServ;
    
    @Autowired
    private UsuarioService usuarioServ;

    @GetMapping("/api/usuariosCompatibles")
    public List<Usuario> getRecomendaciones2(HttpServletRequest request) {
    	String username = (String) request.getAttribute("username");
        Usuario usuarioBase;
		try {
			usuarioBase = usuarioServ.getUsuarioByUsername(username);
			return sistemaRecomendacionServ.recomendarUsuarios(usuarioBase);
			
		} catch (Exception e) {
			e.printStackTrace();
             throw new IllegalArgumentException("Usuario no encontrado");
		} 
    }
    
    @PostMapping("/api/usuariosCompatibles/rechazar/{rechazadoUsername}")
    public void rechazarUsuario( @PathVariable String usernameRechazado, HttpServletRequest request){
    	String username = (String) request.getAttribute("username");

    	try {
	    	Usuario usuarioBase = usuarioServ.getUsuarioByUsername(username);
	        usuarioBase.rechazarUsuario(username);
    	} catch (Exception e) {
			e.printStackTrace();
             throw new IllegalArgumentException("Usuario no encontrado");
		} 
    }
}
