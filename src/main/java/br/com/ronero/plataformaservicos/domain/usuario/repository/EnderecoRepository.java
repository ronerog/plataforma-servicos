package br.com.ronero.plataformaservicos.domain.usuario.repository;

import br.com.ronero.plataformaservicos.domain.usuario.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}