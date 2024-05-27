package gameet.entity;

import java.util.List;
import java.util.Map;

public class JuegoRequest {

	private String id;
	private List<String>  genero;
	private String imagenPortada;
	private Map<String,Integer> consolaNivel;
	private String titulo;
	
	public JuegoRequest(Juego juego, Map<String,Integer>  consolaNivel ) {
		super();
		this.id = juego.getId();
		this.genero = juego.getGenero();
		this.imagenPortada = juego.getImagenPortada();
		this.consolaNivel = consolaNivel;
		this.titulo = juego.getTitulo();
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

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Map<String, Integer> getConsolaNivel() {
		return consolaNivel;
	}
	public void setConsolaNivel(Map<String, Integer> consolaNivel) {
		this.consolaNivel = consolaNivel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
