package gameet.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "enlace")
public class Enlace {

	private Timestamp fechaEnlace;
	private boolean like;
	private String usuarioEntrada;
	private String usuarioSalida;
	
	public Enlace() {
		super();
	}
	public Enlace(Timestamp  fechaEnlace, boolean like, String usuarioEntrada, String usuarioSalida) {
		super();
		this.fechaEnlace = fechaEnlace;
		this.like = like;
		this.usuarioEntrada = usuarioEntrada;
		this.usuarioSalida = usuarioSalida;
	}
	
	@Override
	public String toString() {
		return "Enlace [fechaEnlace=" + fechaEnlace + ", like=" + like + ", usuarioEntrada=" + usuarioEntrada
				+ ", usuarioSalida=" + usuarioSalida + "]";
	}
	
	public Timestamp  getFechaEnlace() {
		return fechaEnlace;
	}
	public void setFechaEnlace(Timestamp   fechaEnlace) {
		this.fechaEnlace = fechaEnlace;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public String getUsuarioEntrada() {
		return usuarioEntrada;
	}
	public void setUsuarioEntrada(String usuarioEntrada) {
		this.usuarioEntrada = usuarioEntrada;
	}
	public String getUsuarioSalida() {
		return usuarioSalida;
	}
	public void setUsuarioSalida(String usuarioSalida) {
		this.usuarioSalida = usuarioSalida;
	}	
	
}
