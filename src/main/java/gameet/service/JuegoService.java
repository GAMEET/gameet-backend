package gameet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import gameet.entity.Juego;
import gameet.entity.JuegosUsuario;

@Service
public class JuegoService {
	
	public List<JuegosUsuario> obtenerJuegosUsuarios(List<String> idsJuegosUsuario) {
		
        List<JuegosUsuario> juegosUsuarios = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();

        for (String id : idsJuegosUsuario) {
            try {
                ApiFuture<DocumentSnapshot> future = db.collection("juegosUsuario").document(id).get();
                DocumentSnapshot document = future.get();

                if (document.exists()) {
                    JuegosUsuario juegosUsuario = document.toObject(JuegosUsuario.class);
                    juegosUsuarios.add(juegosUsuario);
                } else {
                    System.out.println("No se encontró ningún juego de usuario con el ID: " + id);
                }
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error al obtener los juegos de usuario: " + e.getMessage());
            }
        }
        return juegosUsuarios;
    }
	
	 public Juego getJuegoByTitulo(String titulo) throws ExecutionException, InterruptedException {
		 Firestore db = FirestoreClient.getFirestore();
	        DocumentReference docRef = db.collection("juegos").document(titulo);
	        ApiFuture<DocumentSnapshot> future = docRef.get();
	        DocumentSnapshot document = future.get();
	        if (document.exists()) {
	            Juego juego = document.toObject(Juego.class);
	            return juego;
	        } else {
	            return null;
	        }
	    }
	 
	 public JuegosUsuario getJuegoUsuarioByTituloandUsusarioandConsola(String idJuego, String usuario, String consola) throws ExecutionException, InterruptedException {
	        Firestore db = FirestoreClient.getFirestore();
	        CollectionReference juegosUsuarioCollection = db.collection("juegosUsuario");

	        Query query = juegosUsuarioCollection.whereEqualTo("usuario", usuario)
	                                             .whereEqualTo("consola", consola)
	                                             .whereEqualTo("juego", idJuego)
	                                             .limit(1);

	        ApiFuture<QuerySnapshot> querySnapshot = query.get();
	        QuerySnapshot snapshot = querySnapshot.get();

	        if (!snapshot.isEmpty()) {
	            DocumentSnapshot document = snapshot.getDocuments().get(0);
	            return document.toObject(JuegosUsuario.class);
	        } else {
	            return null;
	        }
	    }

	public Juego getJuegoUsuarioById(String id) {
		 Firestore db = FirestoreClient.getFirestore();
	        DocumentReference docRef = db.collection("juegosUsuario").document(id);
	        ApiFuture<DocumentSnapshot> future = docRef.get();
	        DocumentSnapshot document;
			try {
				document = future.get();
			} catch (Exception e) {
				e.printStackTrace();
				document=null;
			} 
	        if (document.exists()) {
	            Juego juego = document.toObject(Juego.class);
	            return juego;
	        } else {
	            return null;
	        }
	}
	
	 public Juego getJuegoById(String id) throws ExecutionException, InterruptedException {
		 Firestore db = FirestoreClient.getFirestore();
	        DocumentReference docRef = db.collection("juegos").document(id);
	        ApiFuture<DocumentSnapshot> future = docRef.get();
	        DocumentSnapshot document = future.get();
	        if (document.exists()) {
	            Juego juego = document.toObject(Juego.class);
	            return juego;
	        } else {
	            return null;
	        }
	    }
}
