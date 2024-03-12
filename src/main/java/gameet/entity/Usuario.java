package gameet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	private Long id;
	private String descripcion;
	private String email;
	private String imagenPerfil;
	private String password;
	private Integer telefono;
	private String username;
	private Caracteristicas caracteristicas;
	private String horarioJuego;
	
	public Usuario(Long id, String descripcion, String email, String imagenPerfil, String password, Integer telefono,
			String username, Caracteristicas caracteristicas, String horarioJuego) {
		super();
		this.setId(id);
		this.descripcion = descripcion;
		this.email = email;
		this.imagenPerfil = imagenPerfil;
		this.password = password;
		this.telefono = telefono;
		this.username = username;
		this.caracteristicas = caracteristicas;
		this.horarioJuego = horarioJuego;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
