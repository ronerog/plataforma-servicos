package br.com.ronero.plataformaservicos.domain.solicitacao.controller;

import br.com.ronero.plataformaservicos.domain.solicitacao.dto.request.CriarSolicitacaoRequest;
import br.com.ronero.plataformaservicos.domain.solicitacao.dto.response.SolicitacaoResponse;
import br.com.ronero.plataformaservicos.domain.solicitacao.service.SolicitacaoService;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/solicitacoes", produces = "application/json")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CIDADAO')")
    public ResponseEntity<SolicitacaoResponse> criarSolicitacao(
            @RequestBody @Valid CriarSolicitacaoRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(solicitacaoService.criarSolicitacao(request, usuarioLogado));
    }

    @GetMapping("/minhas")
    @PreAuthorize("hasAuthority('ROLE_CIDADAO')")
    public ResponseEntity<List<SolicitacaoResponse>> listarMinhasSolicitacoes(
            @AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.ok(solicitacaoService.listarMinhasSolicitacoes(usuarioLogado));
    }
}