package br.com.ronero.plataformaservicos.domain.servico.repository;

import br.com.ronero.plataformaservicos.domain.servico.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByAtivoTrue();

    boolean existsByNomeCategoria(String nomeCategoria);

    Optional<Categoria> findByNomeCategoria(String nomeCategoria);
}