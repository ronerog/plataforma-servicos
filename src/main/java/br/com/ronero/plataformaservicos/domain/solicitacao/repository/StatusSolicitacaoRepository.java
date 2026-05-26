package br.com.ronero.plataformaservicos.domain.solicitacao.repository;

import br.com.ronero.plataformaservicos.domain.solicitacao.entity.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusSolicitacaoRepository extends JpaRepository<StatusSolicitacao, Integer> {

    Optional<StatusSolicitacao> findByNomeStatus(String nomeStatus);
}