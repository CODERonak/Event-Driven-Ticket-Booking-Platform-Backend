package com.product.TicketBookingSystem.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class SwaggerConfig {
        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                                .group("public")
                                .pathsToMatch("/**")
                                .build();
        }

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .servers(List.of(
                                                new Server()
                                                                .url("/http://34.9.189.202")
                                                                .description("Production")))
                                .info(new Info()
                                                .title("Event Driven Booking System")
                                                .description(
                                                                "An event-driven ticket booking system built with Spring Boot. This project uses Spring Modulith for modularity and includes user authentication with JWT. It also integrates with Google Cloud for data persistence.")
                                                .version("1.0.0")
                                                .contact(new Contact()
                                                                .url("https://github.com/CODERonak")))
                                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                                .components(new Components()
                                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                                                .name("bearerAuth")
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")));
        }
}
