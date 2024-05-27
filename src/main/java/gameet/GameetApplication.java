package gameet;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
@ComponentScan(basePackages = {"firestore", "gameet.security", "gameet"})
public class GameetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameetApplication.class, args);
	}

    static {
        // Generar una clave segura para STREAM_SECRET
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String streamSecret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.setProperty("STREAM_SECRET", streamSecret);

        // Generar una clave segura para STREAM_KEY
        SecretKey secretKey2 = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String streamKey = Base64.getEncoder().encodeToString(secretKey2.getEncoded());
        System.setProperty("STREAM_KEY", streamKey);
    }
}
