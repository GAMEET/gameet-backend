package gameet.entity;

public class JuegosUsuarioRequest {
	
	private String juego;
	private Integer nivel;
	private String consola;

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
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

	public void setConsola(String c) {
		this.consola = c;
	}

	}