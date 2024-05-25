package gameet.entity;

import java.util.List;

public class UsuarioPerfilRequest {

	private String descripcion;
	private String email;
	private String imagenPerfil;
	private String password;
	private Integer telefono;
	private List<String> caracteristicas;
	private String horarioJuego;
	private boolean activo;
	
	
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

	
}
