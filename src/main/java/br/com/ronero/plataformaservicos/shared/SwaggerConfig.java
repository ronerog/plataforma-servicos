package br.com.ronero.plataformaservicos.shared;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Plataforma de Serviços Comunitários")
                        .description("API REST para conectar cidadãos e prestadores de serviços voluntários na comunidade")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Rônero Gomes")
                                .email("ronero@email.com"))
                        .license(new License()
                                .name("MIT License")));
    }
}