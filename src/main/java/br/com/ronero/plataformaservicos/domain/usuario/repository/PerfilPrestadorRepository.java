package br.com.ronero.plataformaservicos.domain.usuario.repository;

import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilPrestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerfilPrestadorRepository extends JpaRepository<PerfilPrestador, Integer> {

    Optional<PerfilPrestador> findByUsuarioId(Integer idUsuario);

    boolean existsByCpfCnpj(String cpfCnpj);
}