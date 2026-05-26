package br.com.ronero.plataformaservicos.domain.avaliacao.repository;

import br.com.ronero.plataformaservicos.domain.avaliacao.entity.Avaliacao;
import br.com.ronero.plataformaservicos.shared.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    List<Avaliacao> findBySolicitacaoId(Integer idSolicitacao);

    Optional<Avaliacao> findBySolicitacaoIdAndAvaliador(Integer idSolicitacao, Role avaliador);

    boolean existsBySolicitacaoIdAndAvaliador(Integer idSolicitacao, Role avaliador);
}