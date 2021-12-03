package grupo7.egg.nutrividas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigInteger;

@SpringBootApplication
@EnableJpaAuditing
public class NutrividasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutrividasApplication.class, args);
	}
}
