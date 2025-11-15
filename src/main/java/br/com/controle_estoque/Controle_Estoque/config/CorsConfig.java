package br.com.controle_estoque.Controle_Estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração do CORS (Cross-Origin Resource Sharing).
 *
 * Esta configuração é essencial para permitir que a aplicação front-end
 * (executando em uma origem diferente, como http://localhost:5173)
 * possa fazer requisições para esta API back-end.
 */
@Configuration
public class CorsConfig {

    /**
     * Define o bean {@link WebMvcConfigurer} que registra as regras de CORS.
     *
     * @return A implementação do configurador com as regras de CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * {@inheritDoc}
             *
             * Configura os mapeamentos de CORS para a aplicação.
             *
             * @param registry O registro de CORS onde as regras são adicionadas.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica as regras a todos os endpoints sob /api/
                        // Permite requisições da origem específica do front-end
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}