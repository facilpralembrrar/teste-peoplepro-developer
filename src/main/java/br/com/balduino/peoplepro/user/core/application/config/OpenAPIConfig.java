package br.com.balduino.peoplepro.user.core.application.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI OpenAPI() {
        final String securitySchemeName = "bearerAuth";

        Contact contact = new Contact();
        contact.setEmail("facilpralembrar@gmail.com");
        contact.setName("Anderson Balduino");
        contact.setUrl("https://www.linkedin.com/in/anderson-balduino-0550a65b/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API para gerenciamento usuários")
                .version("1.0")
                .contact(contact)
                .description("Esta API disponibiliza métodos para o controle de usuários.")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
                                .name(securitySchemeName)
                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
