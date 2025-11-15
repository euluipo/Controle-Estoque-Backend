package br.com.controle_estoque.Controle_Estoque.config;

import br.com.controle_estoque.Controle_Estoque.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe de configuração global da aplicação.
 * Define os beans essenciais para o funcionamento do Spring Security,
 * como o PasswordEncoder, UserDetailsService e AuthenticationProvider.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Repositório para acesso aos dados do usuário,
     * utilizado pelo UserDetailsService.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Define o bean do {@link PasswordEncoder} que será usado na aplicação.
     * Utiliza o BCrypt como algoritmo de hashing para as senhas.
     * 
     * @return Uma instância de {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Define o bean do {@link UserDetailsService}.
     *
     * É responsável por buscar o usuário no banco de dados (via {@link UsuarioRepository})
     * pelo nome de usuário.
     *
     * @return A implementação do UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    /**
     * Define o {@link AuthenticationProvider} (provedor de autenticação) da aplicação.
     *
     * Configura o {@link DaoAuthenticationProvider} para usar o {@code UserDetailsService}
     * (para buscar o usuário) e o {@code PasswordEncoder} (para verificar a senha).
     *
     * @return O provedor de autenticação configurado.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Expõe o {@link AuthenticationManager} do Spring Security como um bean gerenciado.
     * Este bean é necessário para processar as requisições de autenticação
     * (ex: no AuthenticationService).
     *
     * @param config A configuração de autenticação injetada pelo Spring.
     * @return O {@link AuthenticationManager} gerenciado.
     * @throws Exception Se houver erro ao obter o AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}