package br.com.ronero.plataformaservicos.domain.servico.controller;

import br.com.ronero.plataformaservicos.domain.servico.dto.request.CriarCategoriaRequest;
import br.com.ronero.plataformaservicos.domain.servico.dto.request.CriarServicoRequest;
import br.com.ronero.plataformaservicos.domain.servico.dto.response.CategoriaResponse;
import br.com.ronero.plataformaservicos.domain.servico.dto.response.ServicoResponse;
import br.com.ronero.plataformaservicos.domain.servico.service.ServicoService;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/servicos", produces = "application/json")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping("/categorias")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CategoriaResponse> criarCategoria(
            @RequestBody @Valid CriarCategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarCategoria(request));
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.ok(servicoService.listarCategorias());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PRESTADOR')")
    public ResponseEntity<ServicoResponse> criarServico(
            @RequestBody @Valid CriarServicoRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServico(request, usuarioLogado));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listarServicos() {
        return ResponseEntity.ok(servicoService.listarServicosPublicados());
    }
}