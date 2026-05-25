package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfil_prestador")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilPrestador extends Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_prestador")
    private Integer id;

    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 20)
    private String cpfCnpj;

    @Column(name = "nome_razao_social", nullable = false, length = 255)
    private String nomeRazaoSocial;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(length = 500)
    private String foto;

    @Column(nullable = false, length = 20)
    private String whatsapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_prestador", nullable = false)
    private StatusPrestador statusPrestador;
}