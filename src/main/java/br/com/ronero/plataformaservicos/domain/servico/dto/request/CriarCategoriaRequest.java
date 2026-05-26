package br.com.ronero.plataformaservicos.domain.servico.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CriarCategoriaRequest {

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nomeCategoria;

    public String getNomeCategoria() { return nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }
}