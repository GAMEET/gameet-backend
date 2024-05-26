package gameet.entity;

import java.util.List;
import java.util.Map;

public class SistemaRecomendacionRequest {
	
	private String descripcion;
	private String email;
	private String imagenPerfil;
	private Integer telefono;
	private String username;
	private List<String> caracteristicas;
	private String horarioJuego;
    Map<String,List<String>> mapaJuegoConsolas;
    
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagenPerfil() {
		return imagenPerfil;
	}
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getHorarioJuego() {
		return horarioJuego;
	}
	public void setHorarioJuego(String horarioJuego) {
		this.horarioJuego = horarioJuego;
	}

	public Map<String, List<String>> getMapaJuegoConsolas() {
		return mapaJuegoConsolas;
	}
	public void setMapaJuegoConsolas(Map<String, List<String>> mapaJuegoConsolas) {
		this.mapaJuegoConsolas = mapaJuegoConsolas;
	}
	
	public SistemaRecomendacionRequest(String descripcion, String email, String imagenPerfil, Integer telefono,
			String username, List<String> caracteristicas, String horarioJuego,
			Map<String, List<String>> mapaJuegoConsolas) {
		super();
		this.descripcion = descripcion;
		this.email = email;
		this.imagenPerfil = imagenPerfil;
		this.telefono = telefono;
		this.username = username;
		this.caracteristicas = caracteristicas;
		this.horarioJuego = horarioJuego;
		this.mapaJuegoConsolas = mapaJuegoConsolas;
	}
	
	public SistemaRecomendacionRequest(Usuario u,
			Map<String, List<String>> mapaJuegoConsolas) {
		super();
		this.descripcion = u.getDescripcion();
		this.email = u.getEmail();
		this.imagenPerfil = u.getImagenPerfil();
		this.telefono = u.getTelefono();
		this.username = u.getUsername();
		this.caracteristicas = u.getCaracteristicas();
		this.horarioJuego = u.getHorarioJuego();
		this.mapaJuegoConsolas = mapaJuegoConsolas;
	}
    
}
