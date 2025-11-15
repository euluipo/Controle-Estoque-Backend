package br.com.controle_estoque.Controle_Estoque.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do SpringDoc OpenAPI (Swagger).
 *
 * Esta classe define as informações gerais da API (título, versão, descrição)
 * através da anotação {@link OpenAPIDefinition} e configura o esquema de
 * segurança JWT (Bearer Auth) para a interface do Swagger UI.
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Oak System API", version = "v1", description = "API para o sistema de controle de estoque"))
public class OpenApiConfig {

    /**
     * Cria e customiza o bean {@link OpenAPI} principal.
     *
     * Esta customização adiciona a definição de um esquema de segurança
     * chamado "bearerAuth" (do tipo HTTP, scheme "bearer", format "JWT")
     * e aplica esse requisito de segurança globalmente a todos os endpoints
     * na documentação do Swagger UI.
     *
     * @return O objeto OpenAPI customizado com o esquema de segurança JWT.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}