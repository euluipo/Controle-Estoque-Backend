package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationRequestDTO;
import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationResponseDTO;
import br.com.controle_estoque.Controle_Estoque.dto.RegisterRequestDTO;
import br.com.controle_estoque.Controle_Estoque.model.Usuario;
import br.com.controle_estoque.Controle_Estoque.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        var usuario = Usuario.builder()
                .nome(request.getNome())
                .usuario(request.getUsuario())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .senha(passwordEncoder.encode(request.getSenha()))
                .build();
        repository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getSenha()
                )
        );
        var usuario = repository.findByUsuario(request.getUsuario())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }
}