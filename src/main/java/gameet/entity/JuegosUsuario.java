package gameet.entity;

public class JuegosUsuario {

	private Integer nivel;
	private Consolas consola;
	private Juego juego;
	
	public JuegosUsuario() {
		super();
	}

	public JuegosUsuario(Integer nivel, Consolas consola, Juego juego) {
		super();
		this.nivel = nivel;
		this.consola = consola;
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "JuegosUsuario [nivel=" + nivel + ", consola=" + consola + ", juego=" + juego + "]";
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Consolas getConsola() {
		return consola;
	}

	public void setConsola(Consolas consola) {
		this.consola = consola;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
}
