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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para Registro e Login")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @SecurityRequirements({})
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    @SecurityRequirements({})
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}