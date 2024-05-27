package gameet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Juego;
import gameet.entity.JuegosUsuario;
import gameet.entity.SistemaRecomendacionRequest;
import gameet.entity.Usuario;
import gameet.entity.UsuarioRequest;
import gameet.service.EnlaceService;
import gameet.service.JuegoService;
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
    
	@Autowired
	private JuegoService juegosServ;

    @GetMapping("/api/usuariosCompatibles")
    public List<SistemaRecomendacionRequest> getRecomendaciones2(HttpServletRequest request) {
    	String username = (String) request.getAttribute("username");
        Usuario usuarioBase;
		try {
			usuarioBase = usuarioServ.getUsuarioByUsername(username);
			List<Usuario>listausuarios = sistemaRecomendacionServ.recomendarUsuarios(usuarioBase);
		    List<SistemaRecomendacionRequest> res = new ArrayList<>();
		    
		    
		    for(Usuario u : listausuarios) {
		    	Map<String,List<String>> mapaJuegoConsolas = new HashMap<>(); 
		    	Set<String> consolasSinDuplicados = new HashSet<>();
		    	
		    	for(String j : u.getJuegos()) {
			    	JuegosUsuario juegoUsuarioCompleto = juegosServ.getJuegoUsuarioById(j);
			    	Juego juegoCompleto = juegosServ.getJuegoById(juegoUsuarioCompleto.getJuego());
			    	
			    	if(mapaJuegoConsolas.containsKey(juegoCompleto.getTitulo())) {
			    		List<String> aux= mapaJuegoConsolas.get(juegoCompleto.getTitulo());
			    		aux.add(juegoUsuarioCompleto.getConsola());
			    		List<String> consolas = new ArrayList<>(consolasSinDuplicados);
				    	mapaJuegoConsolas.put(juegoCompleto.getTitulo(),consolas);	
				    	
			    	}else {
			    		consolasSinDuplicados.add(juegoUsuarioCompleto.getConsola());
			    		List<String> consolas = new ArrayList<>(consolasSinDuplicados);
				    	mapaJuegoConsolas.put(juegoCompleto.getTitulo(),consolas);

			    	}
		    	}
		    	SistemaRecomendacionRequest srr = new SistemaRecomendacionRequest(u,mapaJuegoConsolas );
		    	res.add(srr);
		    	
		    }
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
             throw new IllegalArgumentException("Usuario no encontrado");
		} 
    }
    
    @PostMapping("/api/usuariosCompatibles/interaccion")
    public boolean interaccionUsuario(@RequestBody UsuarioRequest aux, HttpServletRequest request){
    	
    	String usernameCarrusel = aux.getUsername();
    	String username = (String) request.getAttribute("username");

    	try {
    		
	    	Usuario usuarioBase = usuarioServ.getUsuarioByUsername(username);
	    	
	    	if(aux.isLike()) {
		        enlaceServ.interaccionUsuario(username, usernameCarrusel, true);
		        Boolean esMatch =  enlaceServ.getEnlaceByusuarioEntradaAndUsuarioSalida(usernameCarrusel, username); // buscamos si a la inversa tambien existe el like
		        if(esMatch) {
					return true;

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
    	return false;
    }
    

}
