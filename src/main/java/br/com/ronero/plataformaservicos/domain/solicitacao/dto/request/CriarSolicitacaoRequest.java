package br.com.ronero.plataformaservicos.domain.solicitacao.dto.request;

import jakarta.validation.constraints.NotNull;

public class CriarSolicitacaoRequest {

    @NotNull(message = "Serviço é obrigatório")
    private Integer idServico;

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer idServico) { this.idServico = idServico; }
}