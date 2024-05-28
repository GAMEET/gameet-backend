package gameet.entity;

import java.util.List;

public class UsuarioJuegoRequest {
	
	private String descripcion;
	private String email;
	private String imagenPerfil;
	private String password;
	private Integer telefono;
	private String username;
	private List<String> caracteristicas;
	private String horarioJuego;
	private boolean activo;
	private List<String> tituloJuegos;
	
	
	
	public UsuarioJuegoRequest(Usuario usuario, List<String> tituloJuegos) {
		super();
		this.descripcion = usuario.getDescripcion();
		this.email = usuario.getEmail();
		this.imagenPerfil = usuario.getImagenPerfil();
		this.password = usuario.getPassword();
		this.telefono = usuario.getTelefono();
		this.username = usuario.getUsername();
		this.caracteristicas = usuario.getCaracteristicas();
		this.horarioJuego = usuario.getHorarioJuego();
		this.activo = usuario.isActivo();
		this.tituloJuegos = tituloJuegos;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public List<String> getTitulosJuegos() {
		return tituloJuegos;
	}
	public void setJuegos(List<String> tituloJuegos) {
		this.tituloJuegos = tituloJuegos;
	}
	
	
}
