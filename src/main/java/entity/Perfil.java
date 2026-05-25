package entity;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Perfil {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;
}
