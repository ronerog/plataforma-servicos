package br.com.ronero.plataformaservicos.domain.servico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servico_foto")
public class ServicoFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false)
    private Integer ordem;

    public ServicoFoto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }
}
