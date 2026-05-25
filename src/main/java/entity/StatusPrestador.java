package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status_prestador")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusPrestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_prestador")
    private Integer id;

    @Column(name = "nome_status", nullable = false, unique = true, length = 50)
    private String nomeStatus;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}
