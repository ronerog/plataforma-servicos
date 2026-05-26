package br.com.ronero.plataformaservicos.domain.avaliacao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao_foto")
public class AvaliacaoFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avaliacao", nullable = false)
    private Avaliacao avaliacao;

    @Column(nullable = false, length = 500)
    private String url;

    public AvaliacaoFoto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Avaliacao getAvaliacao() { return avaliacao; }
    public void setAvaliacao(Avaliacao avaliacao) { this.avaliacao = avaliacao; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
