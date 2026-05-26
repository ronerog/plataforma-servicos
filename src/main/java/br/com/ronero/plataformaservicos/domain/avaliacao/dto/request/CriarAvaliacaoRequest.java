package br.com.ronero.plataformaservicos.domain.avaliacao.dto.request;

import jakarta.validation.constraints.*;

public class CriarAvaliacaoRequest {

    @NotNull(message = "Solicitação é obrigatória")
    private Integer idSolicitacao;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    private Integer nota;

    @NotBlank(message = "Comentário é obrigatório")
    private String comentario;

    @NotNull(message = "Serviço realizado é obrigatório")
    private Boolean servicoRealizado;

    private String motivoNaoRealizacao;

    public Integer getIdSolicitacao() { return idSolicitacao; }
    public void setIdSolicitacao(Integer idSolicitacao) { this.idSolicitacao = idSolicitacao; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Boolean getServicoRealizado() { return servicoRealizado; }
    public void setServicoRealizado(Boolean servicoRealizado) { this.servicoRealizado = servicoRealizado; }

    public String getMotivoNaoRealizacao() { return motivoNaoRealizacao; }
    public void setMotivoNaoRealizacao(String motivoNaoRealizacao) { this.motivoNaoRealizacao = motivoNaoRealizacao; }
}