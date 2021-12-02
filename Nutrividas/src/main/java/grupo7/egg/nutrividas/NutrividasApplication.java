package grupo7.egg.nutrividas;

import grupo7.egg.nutrividas.servicios.PersonaServicio;
import grupo7.egg.nutrividas.servicios.ProductoServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NutrividasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutrividasApplication.class, args);
	}
}
