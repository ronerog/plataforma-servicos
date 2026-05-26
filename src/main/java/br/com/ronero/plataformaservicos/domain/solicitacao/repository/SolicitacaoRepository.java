package br.com.ronero.plataformaservicos.domain.solicitacao.repository;

import br.com.ronero.plataformaservicos.domain.solicitacao.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {

    List<Solicitacao> findByPerfilCidadaoId(Integer idPerfilCidadao);

    List<Solicitacao> findByServicoPerfilPrestadorId(Integer idPerfilPrestador);

    List<Solicitacao> findByStatusSolicitacaoNomeStatus(String nomeStatus);

    boolean existsByPerfilCidadaoIdAndStatusSolicitacaoNomeStatus(
            Integer idPerfilCidadao, String nomeStatus);

    List<Solicitacao> findByPrazoAvaliacaoBeforeAndStatusSolicitacaoNomeStatus(
            LocalDate data, String nomeStatus);
}