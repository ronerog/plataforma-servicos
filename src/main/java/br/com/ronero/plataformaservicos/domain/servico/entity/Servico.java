package br.com.ronero.plataformaservicos.domain.servico.entity;

import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilPrestador;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil_prestador", nullable = false)
    private PerfilPrestador perfilPrestador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_servico", nullable = false)
    private StatusServico statusServico;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoFoto> fotos = new ArrayList<>();

    public Servico() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PerfilPrestador getPerfilPrestador() { return perfilPrestador; }
    public void setPerfilPrestador(PerfilPrestador perfilPrestador) { this.perfilPrestador = perfilPrestador; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusServico getStatusServico() { return statusServico; }
    public void setStatusServico(StatusServico statusServico) { this.statusServico = statusServico; }

    public List<ServicoFoto> getFotos() { return fotos; }
    public void setFotos(List<ServicoFoto> fotos) { this.fotos = fotos; }
}
