package com.onlinetutoring.evaluationservice.config;


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
                        .title("Online Tutoring Evaluation Service API")
                        .description("API REST du microservice Évaluations pour la plateforme de tutorat en ligne")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yassine LkL")
                                .email("yassineloukili02@gmail.com")
                                .url("https://www.linkedin.com/in/yassine-loukili00/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation complète")
                        .url("https://github.com/username/onlinetutoring-evaluation"));
    }
}
