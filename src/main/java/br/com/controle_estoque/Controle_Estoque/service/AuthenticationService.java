package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationRequestDTO;
import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationResponseDTO;
import br.com.controle_estoque.Controle_Estoque.dto.RegisterRequestDTO;
import br.com.controle_estoque.Controle_Estoque.model.Usuario;
import br.com.controle_estoque.Controle_Estoque.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Serviço responsável pela lógica de autenticação e registro de usuários,
 * gerenciando a criação de contas e a emissão de tokens JWT.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * Repositório responsável pelo acesso e manipulação de dados do {@link Usuario}.
     */
    private final UsuarioRepository repository;

    /**
     * Responsável por criptografar e verificar senhas.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Serviço responsável pela geração e validação de tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Gerenciador de autenticação do Spring Security.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Função: Cadastrar um novo usuário no sistema e gerar um token JWT.
     *
     * @param request O DTO ({@link RegisterRequestDTO}) contendo os dados do novo usuário.
     * @return Um {@link AuthenticationResponseDTO} contendo o token JWT gerado para o usuário registrado.
     */
    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        var usuario = Usuario.builder()
                .nome(request.getNome())
                .usuario(request.getUsuario())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                // Criptografa a senha antes de salvar no banco
                .senha(passwordEncoder.encode(request.getSenha()))
                .build();

        // Salva o usuário no banco de dados
        repository.save(usuario);

        // Gera o token JWT para o novo usuário
        var jwtToken = jwtService.generateToken(usuario);

        // Retorna o token de autenticação
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    /**
     * Função: Autenticar um usuário existente e retornar um token JWT.
     *
     * @param request O DTO ({@link AuthenticationRequestDTO}) contendo as credenciais (usuário e senha).
     * @return Um {@link AuthenticationResponseDTO} contendo o token JWT gerado para o usuário autenticado.
     * @throws AuthenticationException Se as credenciais forem inválidas.
     * @throws NoSuchElementException Se o usuário for autenticado mas não for encontrado no repositório.
     */
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        // Verifica as credenciais do usuário
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getSenha()
                )
        );

        // Busca o usuário autenticado no banco
        var usuario = repository.findByUsuario(request.getUsuario())
                .orElseThrow();

        // Gera um novo token JWT para o usuário
        var jwtToken = jwtService.generateToken(usuario);

        // Retorna o token de autenticação
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }
}