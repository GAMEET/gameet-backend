package gameet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "juegosUsuario")
public class JuegosUsuario {
	
	private String id;
	
	private String usuario;
    
	private Integer nivel;
	
	private String consola;
    
	private String juego;
	
	public JuegosUsuario() {
		super();
	}

	public JuegosUsuario(String usuario, Integer nivel, String consola, String juego) {
		super();
		this.usuario = usuario;
		this.nivel = nivel;
		this.consola = consola;
		this.juego = juego;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getConsola() {
		return consola;
	}

	public void setConsola(String consola) {
		this.consola = consola;
	}

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
