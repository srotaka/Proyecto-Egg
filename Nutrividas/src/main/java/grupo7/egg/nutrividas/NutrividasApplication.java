package grupo7.egg.nutrividas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class NutrividasApplication {
	//private static final Logger log = LoggerFactory.getLogger(NutrividasApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(NutrividasApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Pais pais = restTemplate.getForObject(
					"https://apis.datos.gob.ar/georef/api/provincias", Pais.class);
			log.info(pais.toString());
			pais.getProvincias().forEach(p -> System.out.println(p.getNombre()));
		};
	}*/
}
