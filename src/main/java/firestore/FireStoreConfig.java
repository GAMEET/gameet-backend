package firestore;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FireStoreConfig {
	
	 @Bean
	    public FirebaseApp firebaseApp() throws IOException {
	        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("./gameet-1172e-firebase-adminsdk-8n29z-01059796ed.json");

	        FirebaseOptions options = new FirebaseOptions.Builder()
	                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                .setDatabaseUrl("https://gameet-1172e-default-rtdb.europe-west1.firebasedatabase.app")
	                .build();

	        return FirebaseApp.initializeApp(options);
	    }

    
}