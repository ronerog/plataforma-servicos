package br.com.ronero.plataformaservicos.domain.usuario.repository;

import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilCidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerfilCidadaoRepository extends JpaRepository<PerfilCidadao, Integer> {

    Optional<PerfilCidadao> findByUsuarioId(Integer idUsuario);

    boolean existsByCpf(String cpf);
}