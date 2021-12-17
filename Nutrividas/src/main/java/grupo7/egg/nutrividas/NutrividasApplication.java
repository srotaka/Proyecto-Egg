package grupo7.egg.nutrividas;

import grupo7.egg.nutrividas.mail.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class NutrividasApplication {

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(NutrividasApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


}
