package com.onlinetutoring.service_sessions.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Online Tutoring Sessions Service API")
                        .description("API REST for managing Sessions, Bookings, Schedules, and Materials")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Online Tutoring Team")
                                .email("contact@onlinetutoring.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/MohammedEttalbi/Online-Tutoring-App"));
    }
}
