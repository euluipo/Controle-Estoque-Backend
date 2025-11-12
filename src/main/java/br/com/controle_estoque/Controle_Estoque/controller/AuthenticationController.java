package br.com.controle_estoque.Controle_Estoque.controller;

import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationRequestDTO;
import br.com.controle_estoque.Controle_Estoque.dto.AuthenticationResponseDTO;
import br.com.controle_estoque.Controle_Estoque.dto.RegisterRequestDTO;
import br.com.controle_estoque.Controle_Estoque.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que expõe os endpoints públicos para autenticação e
 * registro de novos usuários no sistema.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para Registro e Login")
public class AuthenticationController {

    /**
     * Serviço injetado para lidar com a lógica de registro e autenticação.
     */
    private final AuthenticationService service;

    /**
     * Endpoint para REGISTRAR um novo usuário no sistema.
     * Este endpoint é público e não requer autenticação.
     *
     * @param request O DTO ({@link RegisterRequestDTO}) contendo os dados do novo usuário.
     * @return Um {@link ResponseEntity} com o {@link AuthenticationResponseDTO} (contendo o token JWT)
     * e status 200 OK.
     */
    @PostMapping("/register")
    @SecurityRequirements({}) // Indica ao Swagger que este endpoint não requer autenticação
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Endpoint para AUTENTICAR (login) um usuário existente.
     * Este endpoint é público e não requer autenticação.
     *
     * @param request O DTO ({@link AuthenticationRequestDTO}) contendo as credenciais (usuário e senha).
     * @return Um {@link ResponseEntity} com o {@link AuthenticationResponseDTO} (contendo o token JWT)
     * e status 200 OK.
     */
    @PostMapping("/login")
    @SecurityRequirements({}) // Indica ao Swagger que este endpoint não requer autenticação
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}