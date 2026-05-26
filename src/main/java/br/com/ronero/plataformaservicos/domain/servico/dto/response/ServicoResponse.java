package br.com.ronero.plataformaservicos.domain.servico.dto.response;

public class ServicoResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private String nomeCategoria;
    private String statusServico;
    private String nomePrestador;

    public ServicoResponse(Integer id, String titulo, String descricao,
                           String nomeCategoria, String statusServico,
                           String nomePrestador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nomeCategoria = nomeCategoria;
        this.statusServico = statusServico;
        this.nomePrestador = nomePrestador;
    }

    public Integer getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getNomeCategoria() { return nomeCategoria; }
    public String getStatusServico() { return statusServico; }
    public String getNomePrestador() { return nomePrestador; }
}