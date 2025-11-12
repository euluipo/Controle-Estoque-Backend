package br.com.controle_estoque.Controle_Estoque.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Serviço responsável por gerenciar operações de JSON Web Token (JWT),
 * como geração, validação e extração de informações.
 */
@Service
public class JwtService {

    /**
     * Chave secreta utilizada para assinar e validar os tokens JWT.
     * ATENÇÃO: Em produção, esta chave deve vir de variáveis de ambiente e não ser "hardcoded".
     */
    private static final String SECRET_KEY = "ChaveSecretaSuperLongaEDificilDeAdivinharPorqueSegurancaImporta";

    /**
     * Função: Extrair o nome de usuário (subject) de um token JWT.
     *
     * @param token O token JWT do qual o usuário será extraído.
     * @return O nome de usuário (subject).
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Função: Gerar um token JWT básico para um usuário.
     *
     * @param userDetails Detalhes do usuário (Spring Security) para quem o token será gerado.
     * @return Uma string representando o token JWT.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Função: Gerar um token JWT com informações adicionais (claims extras).
     *
     * @param extraClaims Um {@link Map} contendo claims adicionais para incluir no token.
     * @param userDetails Detalhes do usuário (Spring Security) para quem o token será gerado.
     * @return Uma string representando o token JWT.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                // Assina o token com a chave secreta e algoritmo HS256
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Função: Validar se o token é válido e pertence ao usuário.
     *
     * @param token O token JWT a ser validado.
     * @param userDetails Os detalhes do usuário para comparação.
     * @return {@code true} se o token for válido (não expirado e pertence ao usuário),
     * {@code false} caso contrário.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Função: Verificar se o token expirou.
     *
     * @param token O token JWT a ser verificado.
     * @return {@code true} se a data de expiração do token for anterior à data atual,
     * {@code false} caso contrário.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * Função: Extrair uma claim específica (informação) do token.
     *
     * @param token O token JWT do qual a claim será extraída.
     * @param claimsResolver Uma função ({@link Function}) que define qual claim extrair.
     * @param <T> O tipo de dado da claim a ser retornada (ex: String, Date, Integer).
     * @return A claim específica resolvida pela função.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Função: Obter a chave de assinatura ({@link Key}) do token a partir da SECRET_KEY (Base64).
     *
     * @return A chave de assinatura tratada para o algoritmo HMAC-SHA.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}