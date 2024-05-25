package gameet.entity;

import java.util.List;

import com.google.firebase.database.PropertyName;

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
	private List<String> caracteristicas;
	private String horarioJuego;
	private boolean activo;
	@PropertyName("juegos")
	private List<String> juegos;
    private List<String> rechazados;   

	 public Usuario(String descripcion, String email, String imagenPerfil, String password, Integer telefono,
			String username, List<String> caracteristicas, String horarioJuego, boolean activo,
			List<String> juegos, List<String> rechazados) {
		super();
		this.descripcion = descripcion;
		this.email = email;
		this.imagenPerfil = imagenPerfil;
		this.password = password;
		this.telefono = telefono;
		this.username = username;
		this.caracteristicas = caracteristicas;
		this.horarioJuego = horarioJuego;
		this.activo = activo;
		this.juegos = juegos;
		this.rechazados = rechazados;
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
	public List<String> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	@PropertyName("juegos")
	public List<String> getJuegos() {
		return juegos;
	}
	@PropertyName("juegos")
	public void setJuegos(List<String> juegos) {
		this.juegos = juegos;
	}
	@PropertyName("rechazados")
	public List<String> getRechazados() {
		return rechazados;
	}
	@PropertyName("rechazados")
	public void setRechazados(List<String> rechazados) {
		this.rechazados = rechazados;
	}

	
}
