package gameet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CaracteristicasService {

	private static final String COL_NAME = "caracteristicas"; // Nombre de la colección de Firestore

	// Método para obtener todas las caracteristicas
	public List<String> getAllCaracteristicas() {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = dbFirestore.collection(COL_NAME).get();
		QuerySnapshot querySnapshot;
		try {
			querySnapshot = query.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		List<String> allCaracteristicas = new ArrayList<>();
		for (QueryDocumentSnapshot document : querySnapshot) {
			DocumentSnapshot doc = (DocumentSnapshot) document;
			allCaracteristicas.add(doc.getData().toString());
		}

		return allCaracteristicas;
	}
}
