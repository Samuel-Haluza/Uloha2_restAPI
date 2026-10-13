package com.example.Uloha2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeeDirectoryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Directory API")
                        .version("1.0")
                        .description("REST API pre správu zamestnancov."));
    }
}
