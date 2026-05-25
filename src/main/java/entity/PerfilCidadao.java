package entity;

import enums.Genero;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "perfil_cidadao")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCidadao extends Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_cidadao")
    private Integer id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Genero genero;

    @Column(name = "foto_perfil", length = 500)
    private String fotoPerfil;
}
