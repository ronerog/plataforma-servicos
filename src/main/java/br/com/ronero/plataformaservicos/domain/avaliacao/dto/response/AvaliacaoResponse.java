package br.com.ronero.plataformaservicos.domain.avaliacao.dto.response;

import java.time.LocalDate;

public class AvaliacaoResponse {

    private Integer id;
    private Integer idSolicitacao;
    private String avaliador;
    private Integer nota;
    private String comentario;
    private Boolean servicoRealizado;
    private LocalDate dataAvaliacao;

    public AvaliacaoResponse(Integer id, Integer idSolicitacao, String avaliador,
                             Integer nota, String comentario,
                             Boolean servicoRealizado, LocalDate dataAvaliacao) {
        this.id = id;
        this.idSolicitacao = idSolicitacao;
        this.avaliador = avaliador;
        this.nota = nota;
        this.comentario = comentario;
        this.servicoRealizado = servicoRealizado;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getId() { return id; }
    public Integer getIdSolicitacao() { return idSolicitacao; }
    public String getAvaliador() { return avaliador; }
    public Integer getNota() { return nota; }
    public String getComentario() { return comentario; }
    public Boolean getServicoRealizado() { return servicoRealizado; }
    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
}