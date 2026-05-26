package br.com.ronero.plataformaservicos.domain.auth.service;

import br.com.ronero.plataformaservicos.domain.auth.dto.request.LoginRequest;
import br.com.ronero.plataformaservicos.domain.auth.dto.response.LoginResponse;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.domain.usuario.repository.UsuarioRepository;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.security.JwtService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NegocioException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenhaHash())) {
            throw new NegocioException("Email ou senha inválidos");
        }

        if (!usuario.getAtivo()) {
            throw new NegocioException("Usuário inativo");
        }

        List<String> roles = usuario.getRoles()
                .stream()
                .map(r -> r.getRole().name())
                .collect(Collectors.toList());

        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        var userDetails = User.withUsername(usuario.getEmail())
                .password(usuario.getSenhaHash())
                .authorities(authorities)
                .build();

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("id", usuario.getId());

        String token = jwtService.gerarToken(userDetails, claims);

        return new LoginResponse(token, usuario.getEmail(), roles);
    }
}