package br.com.ronero.plataformaservicos.domain.auth.controller;

import br.com.ronero.plataformaservicos.domain.auth.dto.request.LoginRequest;
import br.com.ronero.plataformaservicos.domain.auth.dto.response.LoginResponse;
import br.com.ronero.plataformaservicos.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}