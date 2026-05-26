package br.com.ronero.plataformaservicos.domain.usuario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "anexo_usuario")
public class AnexoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "tipo_documento", nullable = false, length = 100)
    private String tipoDocumento;

    public AnexoUsuario() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
}
