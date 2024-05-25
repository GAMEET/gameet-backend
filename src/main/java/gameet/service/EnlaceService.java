package gameet.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import gameet.entity.Enlace;

@Service
public class EnlaceService {

	public void interaccionUsuario(String usernameSesion, String usernameLike, Boolean like) {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		Instant instant = fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant();
		Timestamp timestamp = Timestamp.from(instant);
		Enlace enlace = new Enlace();
		enlace.setFechaEnlace(timestamp);
		enlace.setLike(like);
		enlace.setUsuarioEntrada(usernameSesion);
		enlace.setUsuarioSalida(usernameLike);

		DocumentReference docRef = dbFirestore.collection("enlaces").document();

		try {
			docRef.set(enlace);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public Boolean getEnlaceByusuarioEntradaAndUsuarioSalida(String usuarioEntrada, String usuarioSalida) {
		Firestore db = FirestoreClient.getFirestore();
		CollectionReference enlaceCollection = db.collection("enlaces");

		Query query = enlaceCollection.whereEqualTo("usuarioEntrada", usuarioEntrada)
				.whereEqualTo("usuarioSalida", usuarioSalida).whereEqualTo("like", true).limit(1);

		ApiFuture<QuerySnapshot> querySnapshot = query.get();
		QuerySnapshot snapshot;
		try {
			snapshot = querySnapshot.get();

			if (!snapshot.isEmpty()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return false;
		}

	}

}
