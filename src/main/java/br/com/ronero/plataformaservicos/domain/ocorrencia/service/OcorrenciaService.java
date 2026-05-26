package br.com.ronero.plataformaservicos.domain.ocorrencia.service;

import br.com.ronero.plataformaservicos.domain.ocorrencia.dto.request.CriarOcorrenciaRequest;
import br.com.ronero.plataformaservicos.domain.ocorrencia.dto.response.OcorrenciaResponse;
import br.com.ronero.plataformaservicos.domain.ocorrencia.entity.OcorrenciaUsuario;
import br.com.ronero.plataformaservicos.domain.ocorrencia.repository.OcorrenciaRepository;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.domain.usuario.repository.UsuarioRepository;
import br.com.ronero.plataformaservicos.shared.enums.TipoOcorrencia;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
                             UsuarioRepository usuarioRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public OcorrenciaResponse registrarOcorrencia(CriarOcorrenciaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        if (request.getTipo() == TipoOcorrencia.SUSPENSAO) {
            long totalAlertas = ocorrenciaRepository
                    .countByUsuarioIdAndTipo(usuario.getId(), TipoOcorrencia.ALERTA);

            if (totalAlertas < 2) {
                throw new NegocioException("Usuário precisa ter pelo menos 2 alertas antes de ser suspenso");
            }
        }

        OcorrenciaUsuario ocorrencia = new OcorrenciaUsuario();
        ocorrencia.setUsuario(usuario);
        ocorrencia.setTipo(request.getTipo());
        ocorrencia.setAfetaRole(request.getAfetaRole());
        ocorrencia.setDescricao(request.getDescricao());

        if (request.getTipo() == TipoOcorrencia.SUSPENSAO) {
            ocorrencia.setSuspensaoAte(LocalDate.now().plusDays(60));

            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }

        ocorrenciaRepository.save(ocorrencia);

        return new OcorrenciaResponse(
                ocorrencia.getId(),
                usuario.getId(),
                ocorrencia.getTipo(),
                ocorrencia.getAfetaRole(),
                ocorrencia.getDescricao(),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getSuspensaoAte()
        );
    }

    public List<OcorrenciaResponse> listarOcorrenciasDoUsuario(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }

        return ocorrenciaRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(o -> new OcorrenciaResponse(
                        o.getId(),
                        o.getUsuario().getId(),
                        o.getTipo(),
                        o.getAfetaRole(),
                        o.getDescricao(),
                        o.getDataOcorrencia(),
                        o.getSuspensaoAte()
                ))
                .toList();
    }
}