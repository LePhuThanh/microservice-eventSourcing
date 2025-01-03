package com.phelim.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(title = "Employee Api Specification - Phelim",
            description = "API documentation for Employee Service",
            version = "1.0",
            contact = @Contact(
                    name = "Phelim",
                    email = "Ahihi@gmail.com",
                    url = "https://phelimdepzai.com"
            ),
            license = @License(
                    name = "Phelim License",
                    url = "https://phelimdepzai.com/licenses"
            ),
            termsOfService = "https://phelimdepzai.com/term"
    ),
    servers = {
            @Server(
                    description = "Local ENV",
                    url = "https://localhost:9002"
            ),
            @Server(
                    description = "Dev ENV",
                    url = "https://employee-service.dev.com"
            ),
            @Server(
                    description = "Prod ENV",
                    url = "https://employee-service.dev.com"
            )
    }
)
public class OpenApiConfig {
}
