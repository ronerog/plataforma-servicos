package br.com.ronero.plataformaservicos.domain.solicitacao.entity;

import br.com.ronero.plataformaservicos.domain.servico.entity.Servico;
import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilCidadao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil_cidadao", nullable = false)
    private PerfilCidadao perfilCidadao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao = LocalDate.now();

    @Column(name = "prazo_avaliacao", nullable = false)
    private LocalDate prazoAvaliacao;

    @Column(name = "whatsapp_prestador_snapshot", nullable = false, length = 20)
    private String whatsappPrestadorSnapshot;

    @Column(name = "servico_realizado")
    private Boolean servicoRealizado;

    @Column(name = "motivo_nao_realizacao", columnDefinition = "TEXT")
    private String motivoNaoRealizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_solicitacao", nullable = false)
    private StatusSolicitacao statusSolicitacao;

    public Solicitacao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PerfilCidadao getPerfilCidadao() { return perfilCidadao; }
    public void setPerfilCidadao(PerfilCidadao perfilCidadao) { this.perfilCidadao = perfilCidadao; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public LocalDate getPrazoAvaliacao() { return prazoAvaliacao; }
    public void setPrazoAvaliacao(LocalDate prazoAvaliacao) { this.prazoAvaliacao = prazoAvaliacao; }

    public String getWhatsappPrestadorSnapshot() { return whatsappPrestadorSnapshot; }
    public void setWhatsappPrestadorSnapshot(String whatsappPrestadorSnapshot) { this.whatsappPrestadorSnapshot = whatsappPrestadorSnapshot; }

    public Boolean getServicoRealizado() { return servicoRealizado; }
    public void setServicoRealizado(Boolean servicoRealizado) { this.servicoRealizado = servicoRealizado; }

    public String getMotivoNaoRealizacao() { return motivoNaoRealizacao; }
    public void setMotivoNaoRealizacao(String motivoNaoRealizacao) { this.motivoNaoRealizacao = motivoNaoRealizacao; }

    public StatusSolicitacao getStatusSolicitacao() { return statusSolicitacao; }
    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) { this.statusSolicitacao = statusSolicitacao; }
}
