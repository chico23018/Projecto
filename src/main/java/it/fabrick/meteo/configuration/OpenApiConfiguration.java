package it.fabrick.meteo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi regionOpenApi() {
        return GroupedOpenApi.builder()
                .group("region")
                .packagesToScan("it.fabrick.meteo.controller.region")
                .build();
    }
    @Bean
    public GroupedOpenApi weatherOpenApi() {
        return GroupedOpenApi.builder()
                .group("weather")
                .packagesToScan("it.fabrick.meteo.controller.weather")
                .build();
    }
    @Bean
    public GroupedOpenApi residentOpenApi() {
        return GroupedOpenApi.builder()
                .group("resident")
                .packagesToScan("it.fabrick.meteo.controller.resident")
                .build();
    }
    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Services")
                        .description("A set of  services")
                        .version("0.0.1")
                        .contact(new Contact()
                                .email("acostaj46@gmail.com")
                                .name("Francisco Acosta")));
    }
}
