package gameet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {


	private String descripcion;
	private String email;
	private String imagenPerfil;
	private String password;
	private Integer telefono;
	private String username;
	private Caracteristicas caracteristicas;
	private String horarioJuego;
	
	 public Usuario(String descripcion2, String email2, String imagenPerfil2, String password2, Integer telefono2, String username2, Caracteristicas caracteristicas2, String horarioJuego2) {
			this.descripcion = descripcion2;
			this.email = email2;
			this.imagenPerfil = imagenPerfil2;
			this.password = password2;
			this.telefono = telefono2;
			this.username = username2;
			this.caracteristicas = caracteristicas2;
			this.horarioJuego = horarioJuego2;
	    }
	 public Usuario() {
		
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
	public Caracteristicas getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(Caracteristicas caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getHorarioJuego() {
		return horarioJuego;
	}
	public void setHorarioJuego(String horarioJuego) {
		this.horarioJuego = horarioJuego;
	}
}
