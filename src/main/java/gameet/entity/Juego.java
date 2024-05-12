package gameet.entity;

import java.util.List;

public class Juego {

	private Genero genero;
	private String imagenPortada;
	private List<Consolas> consola;
	private String titulo;
	
	public Juego() {
		super();
	}
	
	public Juego(Genero genero, String imagenPortada, List<Consolas> consola, String titulo) {
		super();
		this.genero = genero;
		this.imagenPortada = imagenPortada;
		this.consola = consola;
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return "Juego [genero=" + genero + ", imagenPortada=" + imagenPortada + ", consola=" + consola + ", titulo="
				+ titulo + "]";
	}
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getImagenPortada() {
		return imagenPortada;
	}
	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	public List<Consolas> getConsola() {
		return consola;
	}
	public void setConsola(List<Consolas> consola) {
		this.consola = consola;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
