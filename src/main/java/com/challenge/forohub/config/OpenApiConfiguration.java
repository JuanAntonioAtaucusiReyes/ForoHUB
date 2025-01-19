package com.challenge.foroHub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("bearer-key",
                new SecurityScheme()
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))
        ).info(new Info()
            .title("Foro HUB")
            .description("API REST Challenge ALURA ONE que tiene la funcionalidades CRUD entre "
                + "Topics, Messages y Users, previamente autenticados implementados mediante "
                + "Spring Security")
            .contact(new Contact()
                .name("Equipo Backend")
                .email("martinchrispalomino@gmail.com"))
            .license(new License()
                .name("Apache 2.0")
                .url("http://forohub.com/api/licencia"))
        );
  }
}
