package br.com.ronero.plataformaservicos.domain.ocorrencia.controller;

import br.com.ronero.plataformaservicos.domain.ocorrencia.dto.request.CriarOcorrenciaRequest;
import br.com.ronero.plataformaservicos.domain.ocorrencia.dto.response.OcorrenciaResponse;
import br.com.ronero.plataformaservicos.domain.ocorrencia.service.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ocorrencias", produces = "application/json")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<OcorrenciaResponse> registrarOcorrencia(
            @RequestBody @Valid CriarOcorrenciaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ocorrenciaService.registrarOcorrencia(request));
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OcorrenciaResponse>> listarOcorrencias(
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(ocorrenciaService.listarOcorrenciasDoUsuario(idUsuario));
    }
}