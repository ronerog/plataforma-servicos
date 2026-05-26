package br.com.ronero.plataformaservicos.domain.ocorrencia.entity;

import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.shared.enums.Role;
import br.com.ronero.plataformaservicos.shared.enums.TipoOcorrencia;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ocorrencia_usuario")
public class OcorrenciaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocorrencia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoOcorrencia tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "afeta_role", nullable = false, length = 20)
    private Role afetaRole;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_ocorrencia", nullable = false)
    private LocalDate dataOcorrencia = LocalDate.now();

    @Column(name = "suspensao_ate")
    private LocalDate suspensaoAte;

    public OcorrenciaUsuario() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoOcorrencia getTipo() { return tipo; }
    public void setTipo(TipoOcorrencia tipo) { this.tipo = tipo; }

    public Role getAfetaRole() { return afetaRole; }
    public void setAfetaRole(Role afetaRole) { this.afetaRole = afetaRole; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataOcorrencia() { return dataOcorrencia; }
    public void setDataOcorrencia(LocalDate dataOcorrencia) { this.dataOcorrencia = dataOcorrencia; }

    public LocalDate getSuspensaoAte() { return suspensaoAte; }
    public void setSuspensaoAte(LocalDate suspensaoAte) { this.suspensaoAte = suspensaoAte; }
}
