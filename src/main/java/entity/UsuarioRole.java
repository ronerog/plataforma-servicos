package entity;

import enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario_role")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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
}