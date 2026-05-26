package br.com.ronero.plataformaservicos.domain.usuario.entity;

import br.com.ronero.plataformaservicos.shared.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_role")
public class UsuarioRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_role")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role;

    public UsuarioRole() {
    }

    public UsuarioRole(Integer id, Usuario usuario, Role role) {
        this.id = id;
        this.usuario = usuario;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}