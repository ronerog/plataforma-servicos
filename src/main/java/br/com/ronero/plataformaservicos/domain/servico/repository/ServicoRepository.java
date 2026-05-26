package br.com.ronero.plataformaservicos.domain.servico.repository;

import br.com.ronero.plataformaservicos.domain.servico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByStatusServicoNomeStatus(String nomeStatus);

    List<Servico> findByCategoriaId(Integer idCategoria);

    List<Servico> findByPerfilPrestadorId(Integer idPerfilPrestador);
}