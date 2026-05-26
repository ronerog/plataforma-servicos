package br.com.ronero.plataformaservicos.domain.ocorrencia.repository;

import br.com.ronero.plataformaservicos.domain.ocorrencia.entity.OcorrenciaUsuario;
import br.com.ronero.plataformaservicos.shared.enums.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<OcorrenciaUsuario, Integer> {

    List<OcorrenciaUsuario> findByUsuarioId(Integer idUsuario);

    List<OcorrenciaUsuario> findByUsuarioIdAndTipo(Integer idUsuario, TipoOcorrencia tipo);

    long countByUsuarioIdAndTipo(Integer idUsuario, TipoOcorrencia tipo);
}