package gameet.entity;

import java.util.List;

public class Juego {

	private String genero;
	private String imagenPortada;
	private List<String> consola;
	private String titulo;
	
	public Juego() {
		super();
	}
	
	public Juego(String genero, String imagenPortada, List<String> consola, String titulo) {
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
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getImagenPortada() {
		return imagenPortada;
	}
	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	public List<String> getConsola() {
		return consola;
	}
	public void setConsola(List<String> consola) {
		this.consola = consola;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
