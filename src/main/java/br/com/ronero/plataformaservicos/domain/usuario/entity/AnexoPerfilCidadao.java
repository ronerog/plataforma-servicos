package br.com.ronero.plataformaservicos.domain.usuario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "anexo_perfil_cidadao")
public class AnexoPerfilCidadao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil_cidadao", nullable = false)
    private PerfilCidadao perfilCidadao;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "tipo_documento", nullable = false, length = 100)
    private String tipoDocumento;

    public AnexoPerfilCidadao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PerfilCidadao getPerfilCidadao() { return perfilCidadao; }
    public void setPerfilCidadao(PerfilCidadao perfilCidadao) { this.perfilCidadao = perfilCidadao; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
}
