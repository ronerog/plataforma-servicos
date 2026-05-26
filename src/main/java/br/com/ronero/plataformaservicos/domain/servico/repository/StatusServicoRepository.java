package br.com.ronero.plataformaservicos.domain.servico.repository;

import br.com.ronero.plataformaservicos.domain.servico.entity.StatusServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusServicoRepository extends JpaRepository<StatusServico, Integer> {

    Optional<StatusServico> findByNomeStatus(String nomeStatus);
}