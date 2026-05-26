package br.com.ronero.plataformaservicos.domain.usuario.controller;

import br.com.ronero.plataformaservicos.domain.usuario.dto.request.CadastroCidadaoRequest;
import br.com.ronero.plataformaservicos.domain.usuario.dto.request.CadastroPrestadorRequest;
import br.com.ronero.plataformaservicos.domain.usuario.dto.response.PerfilCidadaoResponse;
import br.com.ronero.plataformaservicos.domain.usuario.dto.response.PerfilPrestadorResponse;
import br.com.ronero.plataformaservicos.domain.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cidadao")
    public ResponseEntity<PerfilCidadaoResponse> cadastrarCidadao(
            @RequestBody @Valid CadastroCidadaoRequest request) {
        PerfilCidadaoResponse response = usuarioService.cadastrarCidadao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/prestador")
    public ResponseEntity<PerfilPrestadorResponse> cadastrarPrestador(
            @RequestBody @Valid CadastroPrestadorRequest request) {
        PerfilPrestadorResponse response = usuarioService.cadastrarPrestador(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/cidadaos", produces = "application/json")
    public ResponseEntity<List<PerfilCidadaoResponse>> listarCidadaos() {
        return ResponseEntity.ok(usuarioService.listarCidadaos());
    }

    @GetMapping(value = "/prestadores", produces = "application/json")
    public ResponseEntity<List<PerfilPrestadorResponse>> listarPrestadores() {
        return ResponseEntity.ok(usuarioService.listarPrestadores());
    }

    @GetMapping(value = "/cidadao/{idUsuario}", produces = "application/json")
    public ResponseEntity<PerfilCidadaoResponse> buscarCidadao(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(usuarioService.buscarCidadaoPorUsuario(idUsuario));
    }
}