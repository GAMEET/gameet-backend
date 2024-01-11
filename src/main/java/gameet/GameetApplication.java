package gameet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"firestore", "gameet"})
public class GameetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameetApplication.class, args);
	}

}
