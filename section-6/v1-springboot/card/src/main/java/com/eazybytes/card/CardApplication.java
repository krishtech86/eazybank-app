package com.eazybytes.card;

import com.eazybytes.card.dto.CardContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "EazyBank Cards microservice REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Krishna Jadhav",
						email = "krishna12@gmail.com",
						url = "https://www.krishtech.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.krishtech.com"
				)
		),
				externalDocs = @ExternalDocumentation(
						description = "EazyBank Cards microservices REST API Documentation",
						url = "https://www.krishtech.com"

		)
)
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
