package gameet.entity;

import java.util.Date;

import com.google.firebase.database.PropertyName;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "enlace")
public class Enlace {

	@PropertyName("fechaEnlace")
	private Date fechaEnlace;
	@PropertyName("like")
	private boolean like;
	private String usuarioEntrada;
	private String usuarioSalida;
	
	public Enlace() {
		super();
	}
	public Enlace(Date  fechaEnlace, boolean like, String usuarioEntrada, String usuarioSalida) {
		super();
		this.fechaEnlace = fechaEnlace;
		this.like = like;
		this.usuarioEntrada = usuarioEntrada;
		this.usuarioSalida = usuarioSalida;
	}
	
	public Enlace(boolean like, Date fechaEnlace, String usuarioSalida, String usuarioEntrada) {
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
	@PropertyName("fechaEnlace")
	public Date  getFechaEnlace() {
		return fechaEnlace;
	}
	@PropertyName("fechaEnlace")
	public void setFechaEnlace(Date   fechaEnlace) {
		this.fechaEnlace = fechaEnlace;
	}
	@PropertyName("like")
	public boolean isLike() {
		return like;
	}
	@PropertyName("like")
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
