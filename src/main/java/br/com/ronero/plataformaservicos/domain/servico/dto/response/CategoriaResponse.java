package br.com.ronero.plataformaservicos.domain.servico.dto.response;

public class CategoriaResponse {

    private Integer id;
    private String nomeCategoria;
    private Boolean ativo;

    public CategoriaResponse(Integer id, String nomeCategoria, Boolean ativo) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.ativo = ativo;
    }

    public Integer getId() { return id; }
    public String getNomeCategoria() { return nomeCategoria; }
    public Boolean getAtivo() { return ativo; }
}