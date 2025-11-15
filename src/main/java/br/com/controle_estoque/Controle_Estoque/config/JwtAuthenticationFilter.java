package br.com.controle_estoque.Controle_Estoque.config;

import br.com.controle_estoque.Controle_Estoque.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticação que intercepta todas as requisições HTTP
 * para processar e validar o token JWT.
 *
 * Este filtro é executado uma vez por requisição (graças a {@link OncePerRequestFilter})
 * e é responsável por extrair o token do cabeçalho 'Authorization',
 * validá-lo e, se for válido, configurar o {@link SecurityContextHolder}
 * com os dados do usuário autenticado, permitindo que a requisição prossiga
 * para os endpoints protegidos.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Serviço para manipulação (extração, validação) do token JWT.
     */
    private final JwtService jwtService;

    /**
     * Serviço para buscar os detalhes do usuário (UserDetails) no banco de dados.
     */
    private final UserDetailsService userDetailsService;

    /**
     * {@inheritDoc}
     *
     * Executa a lógica principal do filtro de autenticação JWT.
     * Tenta extrair um token "Bearer" do cabeçalho 'Authorization'.
     * Se um token for encontrado e validado, ele define a autenticação
     * no {@link SecurityContextHolder}.
     *
     * @param request A requisição HTTP de entrada.
     * @param response A resposta HTTP de saída.
     * @param filterChain O mecanismo para invocar o próximo filtro na cadeia.
     * @throws ServletException Se ocorrer um erro durante o processamento do servlet.
     * @throws IOException Se ocorrer um erro de I/O.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Verifica se o cabeçalho existe e contem "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token do cabeçalho
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        // Valida o token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}