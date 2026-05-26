package br.com.ronero.plataformaservicos.domain.servico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "anexo_servico")
public class AnexoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "tipo_documento", nullable = false, length = 100)
    private String tipoDocumento;

    public AnexoServico() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
}