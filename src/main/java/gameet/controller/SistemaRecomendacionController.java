package gameet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.entity.UsuarioRequest;
import gameet.service.EnlaceService;
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
    
    @Autowired
    private EnlaceService enlaceServ;

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
    
    @PostMapping("/api/usuariosCompatibles/interaccion")
    public void interaccionUsuario(@RequestBody UsuarioRequest aux, HttpServletRequest request){
    	
    	String usernameCarrusel = aux.getUsername();
    	String username = (String) request.getAttribute("username");

    	try {
    		
	    	Usuario usuarioBase = usuarioServ.getUsuarioByUsername(username);
	    	
	    	if(aux.isLike()) {
		        enlaceServ.interaccionUsuario(username, usernameCarrusel, true);
		        Boolean esMatch =  enlaceServ.getEnlaceByusuarioEntradaAndUsuarioSalida(usernameCarrusel, username); // buscamos si a la inversa tambien existe el like
		        if(esMatch) {
					System.err.println("ES MATCH PERO FALTA EL CHAT");

		        }
	    	}else {
	    		
		        List<String>rechazados = usuarioBase.getRechazados();
		        
		        if (!rechazados.contains(usernameCarrusel)) {
		            rechazados.add(usernameCarrusel);
		        }
		        usuarioBase.setRechazados(rechazados);
		        usuarioServ.guardarUsuario(usuarioBase);
		      
		        enlaceServ.interaccionUsuario(username, usernameCarrusel, false);
	    	}

    	} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    

}
