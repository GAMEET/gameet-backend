package gameet.entity;

import java.util.List;

public class JuegoRequest {

	private List<String>  genero;
	private String imagenPortada;
	private List<String> consolas;
	private String titulo;
	private Integer nivel;
	
	public JuegoRequest(Juego juego,Integer nivel, List<String> consolas ) {
		super();
		this.genero = juego.getGenero();
		this.imagenPortada = juego.getImagenPortada();
		this.consolas = consolas;
		this.titulo = juego.getTitulo();
		this.nivel = nivel;
	}
	public List<String> getGenero() {
		return genero;
	}
	public void setGenero(List<String> genero) {
		this.genero = genero;
	}
	public String getImagenPortada() {
		return imagenPortada;
	}
	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	public List<String> getConsolas() {
		return consolas;
	}
	public void setConsolas(List<String> consolas) {
		this.consolas = consolas;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	
}
