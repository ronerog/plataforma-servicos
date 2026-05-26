package br.com.ronero.plataformaservicos.domain.usuario.repository;

import br.com.ronero.plataformaservicos.domain.usuario.entity.StatusPrestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatusPrestadorRepository extends JpaRepository<StatusPrestador, Integer> {

    Optional<StatusPrestador> findByNomeStatus(String nomeStatus);
}