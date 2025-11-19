package utp.edu.pe.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppRestauranteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRestauranteApiApplication.class, args);
	}

}
