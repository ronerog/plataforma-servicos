package br.com.ronero.plataformaservicos.domain.avaliacao.entity;

import br.com.ronero.plataformaservicos.domain.solicitacao.entity.Solicitacao;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.shared.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "br/com/ronero/plataformaservicos/domain/avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private Solicitacao solicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role avaliador;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao = LocalDate.now();

    @OneToMany(mappedBy = "br/com/ronero/plataformaservicos/domain/avaliacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvaliacaoFoto> fotos = new ArrayList<>();

    public Avaliacao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Solicitacao getSolicitacao() { return solicitacao; }
    public void setSolicitacao(Solicitacao solicitacao) { this.solicitacao = solicitacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Role getAvaliador() { return avaliador; }
    public void setAvaliador(Role avaliador) { this.avaliador = avaliador; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    public List<AvaliacaoFoto> getFotos() { return fotos; }
    public void setFotos(List<AvaliacaoFoto> fotos) { this.fotos = fotos; }
}
