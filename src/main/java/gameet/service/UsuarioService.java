package gameet.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import gameet.entity.Usuario;

@Service
public class UsuarioService {

    private static final String COL_NAME = "usuarios"; // Nombre de la colección de Firestore

    public Usuario getUsuarioById(Long id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Usuario.class);
        } else {
            return null;
        }
    }

    public Usuario getUsuarioByDescripcion(String descripcion) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("descripcion", descripcion).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; 
    }

 // Método para obtener un usuario por su dirección de correo electrónico
    public Usuario getUsuarioByEmail(String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("email", email).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con el correo electrónico dado
    }

    // Método para obtener un usuario por su imagen de perfil
    public Usuario getUsuarioByImagenPerfil(String imagenPerfil) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("imagenPerfil", imagenPerfil).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con la imagen de perfil dada
    }

 // Método para obtener un usuario por su contraseña
    public Usuario getUsuarioByPassword(String password) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("password", password).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con la contraseña dada
    }

    // Método para obtener un usuario por su número de teléfono
    public Usuario getUsuarioByTelefono(Integer telefono) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("telefono", telefono).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con el número de teléfono dado
    }

    // Método para obtener un usuario por su nombre de usuario
    public Usuario getUsuarioByUsername(String username) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("username", username).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con el nombre de usuario dado
    }

    // Método para obtener un usuario por sus características
    public Usuario getUsuarioByCaracteristicas(String caracteristicas) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("caracteristicas", caracteristicas).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con las características dadas
    }

    // Método para obtener un usuario por su horario de juego
    public Usuario getUsuarioByHorarioJuego(String horarioJuego) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).whereEqualTo("horarioJuego", horarioJuego).get();
        QuerySnapshot querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            if (document.exists()) {
                return document.toObject(Usuario.class);
            }
        }

        return null; // Si no se encuentra ningún usuario con el horario de juego dado
    }
    
    public void guardarUsuario(Usuario usuario) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COL_NAME).document(usuario.getUsername().toString());
        docRef.set(usuario);
        docRef.create(usuario);
        
    }


}
