package br.com.ronero.plataformaservicos.domain.solicitacao.dto.response;

import java.time.LocalDate;

public class SolicitacaoResponse {

    private Integer id;
    private String nomeServico;
    private String nomePrestador;
    private String whatsappPrestador;
    private LocalDate dataSolicitacao;
    private LocalDate prazoAvaliacao;
    private String statusSolicitacao;

    public SolicitacaoResponse(Integer id, String nomeServico, String nomePrestador,
                               String whatsappPrestador, LocalDate dataSolicitacao,
                               LocalDate prazoAvaliacao, String statusSolicitacao) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.nomePrestador = nomePrestador;
        this.whatsappPrestador = whatsappPrestador;
        this.dataSolicitacao = dataSolicitacao;
        this.prazoAvaliacao = prazoAvaliacao;
        this.statusSolicitacao = statusSolicitacao;
    }

    public Integer getId() { return id; }
    public String getNomeServico() { return nomeServico; }
    public String getNomePrestador() { return nomePrestador; }
    public String getWhatsappPrestador() { return whatsappPrestador; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public LocalDate getPrazoAvaliacao() { return prazoAvaliacao; }
    public String getStatusSolicitacao() { return statusSolicitacao; }
}