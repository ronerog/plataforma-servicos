package br.com.ronero.plataformaservicos.domain.avaliacao.service;

import br.com.ronero.plataformaservicos.domain.avaliacao.dto.request.CriarAvaliacaoRequest;
import br.com.ronero.plataformaservicos.domain.avaliacao.dto.response.AvaliacaoResponse;
import br.com.ronero.plataformaservicos.domain.avaliacao.entity.Avaliacao;
import br.com.ronero.plataformaservicos.domain.avaliacao.repository.AvaliacaoRepository;
import br.com.ronero.plataformaservicos.domain.solicitacao.entity.Solicitacao;
import br.com.ronero.plataformaservicos.domain.solicitacao.repository.SolicitacaoRepository;
import br.com.ronero.plataformaservicos.domain.solicitacao.repository.StatusSolicitacaoRepository;
import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilCidadao;
import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilPrestador;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.domain.usuario.repository.PerfilCidadaoRepository;
import br.com.ronero.plataformaservicos.domain.usuario.repository.PerfilPrestadorRepository;
import br.com.ronero.plataformaservicos.shared.enums.Role;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final SolicitacaoRepository solicitacaoRepository;
    private final StatusSolicitacaoRepository statusSolicitacaoRepository;
    private final PerfilCidadaoRepository perfilCidadaoRepository;
    private final PerfilPrestadorRepository perfilPrestadorRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository,
                            SolicitacaoRepository solicitacaoRepository,
                            StatusSolicitacaoRepository statusSolicitacaoRepository,
                            PerfilCidadaoRepository perfilCidadaoRepository,
                            PerfilPrestadorRepository perfilPrestadorRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.statusSolicitacaoRepository = statusSolicitacaoRepository;
        this.perfilCidadaoRepository = perfilCidadaoRepository;
        this.perfilPrestadorRepository = perfilPrestadorRepository;
    }

    public AvaliacaoResponse avaliarComoCidadao(CriarAvaliacaoRequest request, Usuario usuarioLogado) {
        PerfilCidadao perfil = perfilCidadaoRepository.findByUsuarioId(usuarioLogado.getId())
                .orElseThrow(() -> new NegocioException("Usuário não possui perfil de cidadão"));

        Solicitacao solicitacao = solicitacaoRepository.findById(request.getIdSolicitacao())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));

        if (!solicitacao.getPerfilCidadao().getId().equals(perfil.getId())) {
            throw new NegocioException("Você não é o cidadão desta solicitação");
        }

        if (avaliacaoRepository.existsBySolicitacaoIdAndAvaliador(
                solicitacao.getId(), Role.ROLE_CIDADAO)) {
            throw new NegocioException("Você já avaliou esta solicitação");
        }

        if (LocalDate.now().isAfter(solicitacao.getPrazoAvaliacao())) {
            throw new NegocioException("Prazo de avaliação encerrado");
        }

        if (!request.getServicoRealizado() &&
                (request.getMotivoNaoRealizacao() == null || request.getMotivoNaoRealizacao().isBlank())) {
            throw new NegocioException("Motivo de não realização é obrigatório");
        }

        solicitacao.setServicoRealizado(request.getServicoRealizado());
        solicitacao.setMotivoNaoRealizacao(request.getMotivoNaoRealizacao());
        solicitacaoRepository.save(solicitacao);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setSolicitacao(solicitacao);
        avaliacao.setUsuario(usuarioLogado);
        avaliacao.setAvaliador(Role.ROLE_CIDADAO);
        avaliacao.setNota(request.getNota());
        avaliacao.setComentario(request.getComentario());
        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponse(
                avaliacao.getId(),
                solicitacao.getId(),
                avaliacao.getAvaliador().name(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                solicitacao.getServicoRealizado(),
                avaliacao.getDataAvaliacao()
        );
    }

    public AvaliacaoResponse avaliarComoPrestador(CriarAvaliacaoRequest request, Usuario usuarioLogado) {
        PerfilPrestador perfil = perfilPrestadorRepository.findByUsuarioId(usuarioLogado.getId())
                .orElseThrow(() -> new NegocioException("Usuário não possui perfil de prestador"));

        Solicitacao solicitacao = solicitacaoRepository.findById(request.getIdSolicitacao())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));

        if (!solicitacao.getServico().getPerfilPrestador().getId().equals(perfil.getId())) {
            throw new NegocioException("Você não é o prestador desta solicitação");
        }

        if (avaliacaoRepository.existsBySolicitacaoIdAndAvaliador(
                solicitacao.getId(), Role.ROLE_PRESTADOR)) {
            throw new NegocioException("Você já avaliou esta solicitação");
        }

        if (LocalDate.now().isAfter(solicitacao.getPrazoAvaliacao())) {
            throw new NegocioException("Prazo de avaliação encerrado");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setSolicitacao(solicitacao);
        avaliacao.setUsuario(usuarioLogado);
        avaliacao.setAvaliador(Role.ROLE_PRESTADOR);
        avaliacao.setNota(request.getNota());
        avaliacao.setComentario(request.getComentario());
        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponse(
                avaliacao.getId(),
                solicitacao.getId(),
                avaliacao.getAvaliador().name(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                solicitacao.getServicoRealizado(),
                avaliacao.getDataAvaliacao()
        );
    }

    public List<AvaliacaoResponse> listarAvaliacoesDaSolicitacao(Integer idSolicitacao) {
        return avaliacaoRepository.findBySolicitacaoId(idSolicitacao)
                .stream()
                .map(a -> new AvaliacaoResponse(
                        a.getId(),
                        a.getSolicitacao().getId(),
                        a.getAvaliador().name(),
                        a.getNota(),
                        a.getComentario(),
                        a.getSolicitacao().getServicoRealizado(),
                        a.getDataAvaliacao()
                ))
                .toList();
    }
}