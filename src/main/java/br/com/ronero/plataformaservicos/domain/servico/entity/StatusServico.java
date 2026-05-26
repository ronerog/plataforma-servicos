package br.com.ronero.plataformaservicos.domain.servico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status_servico")
public class StatusServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_servico")
    private Integer id;

    @Column(name = "nome_status", nullable = false, unique = true, length = 50)
    private String nomeStatus;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public StatusServico() {}

    public StatusServico(Integer id, String nomeStatus, String descricao) {
        this.id = id;
        this.nomeStatus = nomeStatus;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeStatus() { return nomeStatus; }
    public void setNomeStatus(String nomeStatus) { this.nomeStatus = nomeStatus; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
