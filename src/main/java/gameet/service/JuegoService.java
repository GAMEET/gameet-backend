package gameet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

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
}
