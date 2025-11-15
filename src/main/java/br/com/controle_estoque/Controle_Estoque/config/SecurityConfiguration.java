package br.com.controle_estoque.Controle_Estoque.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Classe de configuração principal do Spring Security.
 * Habilita a segurança web (@EnableWebSecurity) e define a cadeia de filtros
 * de segurança (SecurityFilterChain) que protege a aplicação.
 *
 * Esta classe configura o CSRF, CORS, gerenciamento de sessão (stateless),
 * regras de autorização de rotas e o filtro de autenticação JWT.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * Filtro customizado para autenticação via JWT.
     * Será adicionado à cadeia de filtros do Spring Security.
     * @see JwtAuthenticationFilter
     */
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Provedor de autenticação customizado (configurado em ApplicationConfig)
     * que usa o UserDetailsService e PasswordEncoder.
     * @see ApplicationConfig#authenticationProvider()
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * Define e configura a cadeia de filtros de segurança (SecurityFilterChain).
     * Este bean é o núcleo da configuração de segurança do Spring.
     *
     * A configuração inclui a desabilitação do CSRF, habilitação do CORS,
     * autorização de requisições (definindo rotas públicas e protegidas),
     * gerenciamento de sessão como STATELESS, definição do provedor de
     * autenticação e a adição do filtro JWT (jwtAuthFilter) antes do
     * filtro padrão de autenticação.
     *
     * @param http O construtor HttpSecurity para configurar a cadeia.
     * @return A {@link SecurityFilterChain} construída.
     * @throws Exception Se ocorrer um erro during a configuração.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Define quais rotas são públicas
                        .requestMatchers(
                                "/api/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**"
                        ).permitAll()

                        // Define que todas as outras requisições precisam de autenticação
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}