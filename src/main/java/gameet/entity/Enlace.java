package gameet.entity;

import java.time.LocalDate;

public class Enlace {

	private LocalDate fechaEnlace;
	private boolean like;
	private Usuario usuarioEntrada;
	private Usuario usuarioSalida;
	
	public Enlace() {
		super();
	}
	public Enlace(LocalDate fechaEnlace, boolean like, Usuario usuarioEntrada, Usuario usuarioSalida) {
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
	
	public LocalDate getFechaEnlace() {
		return fechaEnlace;
	}
	public void setFechaEnlace(LocalDate fechaEnlace) {
		this.fechaEnlace = fechaEnlace;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public Usuario getUsuarioEntrada() {
		return usuarioEntrada;
	}
	public void setUsuarioEntrada(Usuario usuarioEntrada) {
		this.usuarioEntrada = usuarioEntrada;
	}
	public Usuario getUsuarioSalida() {
		return usuarioSalida;
	}
	public void setUsuarioSalida(Usuario usuarioSalida) {
		this.usuarioSalida = usuarioSalida;
	}	
	
}
