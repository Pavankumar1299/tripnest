package com.tripnest.booking.config;

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
    public OpenAPI tripNestHotelAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("TripNest Booking Service API")

                        .description("Booking Management APIs for TripNest Booking System")

                        .version("1.0")

                        .contact(new Contact()

                                .name("Pavan Pattar")

                                .email("pavanvp1299@gmail.com"))

                        .license(new License()

                                .name("Apache 2.0")))

                .externalDocs(new ExternalDocumentation()

                        .description("TripNest Documentation"));
    }
}