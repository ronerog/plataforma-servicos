package br.com.ronero.plataformaservicos.domain.avaliacao.controller;

import br.com.ronero.plataformaservicos.domain.avaliacao.dto.request.CriarAvaliacaoRequest;
import br.com.ronero.plataformaservicos.domain.avaliacao.dto.response.AvaliacaoResponse;
import br.com.ronero.plataformaservicos.domain.avaliacao.service.AvaliacaoService;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/avaliacoes", produces = "application/json")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/cidadao")
    @PreAuthorize("hasAuthority('ROLE_CIDADAO')")
    public ResponseEntity<AvaliacaoResponse> avaliarComoCidadao(
            @RequestBody @Valid CriarAvaliacaoRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.avaliarComoCidadao(request, usuarioLogado));
    }

    @PostMapping("/prestador")
    @PreAuthorize("hasAuthority('ROLE_PRESTADOR')")
    public ResponseEntity<AvaliacaoResponse> avaliarComoPrestador(
            @RequestBody @Valid CriarAvaliacaoRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.avaliarComoPrestador(request, usuarioLogado));
    }

    @GetMapping("/solicitacao/{idSolicitacao}")
    public ResponseEntity<List<AvaliacaoResponse>> listarAvaliacoes(
            @PathVariable Integer idSolicitacao) {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoesDaSolicitacao(idSolicitacao));
    }
}