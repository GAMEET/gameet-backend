package gameet.entity;

import java.util.List;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.PropertyName;

import jakarta.persistence.Table;
@Table(name = "juegos")
public class Juego {

	@DocumentId
	private String id;
	@PropertyName("genero")
	private List<String>  genero;
	private String imagenPortada;
	@PropertyName("consolas")
	private List<String> consolas;
	private String titulo;
	
	public Juego() {
		super();
	}
	
	public Juego(List<String> genero, String imagenPortada, List<String> consola, String titulo) {
		super();
		this.genero = genero;
		this.imagenPortada = imagenPortada;
		this.consolas = consola;
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return "Juego [genero=" + genero + ", imagenPortada=" + imagenPortada + ", consola=" + consolas + ", titulo="
				+ titulo + "]";
	}
	@PropertyName("genero")
	public List<String> getGenero() {
		return genero;
	}
	@PropertyName("genero")
	public void setGenero(List<String> genero) {
		this.genero = genero;
	}
	public String getImagenPortada() {
		return imagenPortada;
	}
	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	
    @PropertyName("consolas")
    public List<String> getConsolas() {
        return consolas;
    }

    @PropertyName("consolas")
    public void setConsolas(List<String> consolas) {
        this.consolas = consolas;
    }
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
