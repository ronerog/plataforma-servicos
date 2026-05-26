package br.com.ronero.plataformaservicos.domain.solicitacao.service;

import br.com.ronero.plataformaservicos.domain.solicitacao.dto.request.CriarSolicitacaoRequest;
import br.com.ronero.plataformaservicos.domain.solicitacao.dto.response.SolicitacaoResponse;
import br.com.ronero.plataformaservicos.domain.solicitacao.entity.Solicitacao;
import br.com.ronero.plataformaservicos.domain.solicitacao.entity.StatusSolicitacao;
import br.com.ronero.plataformaservicos.domain.solicitacao.repository.SolicitacaoRepository;
import br.com.ronero.plataformaservicos.domain.solicitacao.repository.StatusSolicitacaoRepository;
import br.com.ronero.plataformaservicos.domain.servico.entity.Servico;
import br.com.ronero.plataformaservicos.domain.servico.repository.ServicoRepository;
import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilCidadao;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.domain.usuario.repository.PerfilCidadaoRepository;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final StatusSolicitacaoRepository statusSolicitacaoRepository;
    private final ServicoRepository servicoRepository;
    private final PerfilCidadaoRepository perfilCidadaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository,
                              StatusSolicitacaoRepository statusSolicitacaoRepository,
                              ServicoRepository servicoRepository,
                              PerfilCidadaoRepository perfilCidadaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.statusSolicitacaoRepository = statusSolicitacaoRepository;
        this.servicoRepository = servicoRepository;
        this.perfilCidadaoRepository = perfilCidadaoRepository;
    }

    public SolicitacaoResponse criarSolicitacao(CriarSolicitacaoRequest request, Usuario usuarioLogado) {
        PerfilCidadao perfil = perfilCidadaoRepository.findByUsuarioId(usuarioLogado.getId())
                .orElseThrow(() -> new NegocioException("Usuário não possui perfil de cidadão"));

        boolean temAvaliacaoPendente = solicitacaoRepository
                .existsByPerfilCidadaoIdAndStatusSolicitacaoNomeStatus(perfil.getId(), "PENDENTE");

        if (temAvaliacaoPendente) {
            throw new NegocioException("Você possui avaliações pendentes. Conclua-as antes de fazer novas solicitações");
        }

        Servico servico = servicoRepository.findById(request.getIdServico())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado"));

        if (!servico.getStatusServico().getNomeStatus().equals("PUBLICADO")) {
            throw new NegocioException("Serviço não está disponível");
        }

        StatusSolicitacao statusPendente = statusSolicitacaoRepository
                .findByNomeStatus("PENDENTE")
                .orElseThrow(() -> new RecursoNaoEncontradoException("Status não encontrado"));

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setPerfilCidadao(perfil);
        solicitacao.setServico(servico);
        solicitacao.setWhatsappPrestadorSnapshot(servico.getPerfilPrestador().getWhatsapp());
        solicitacao.setStatusSolicitacao(statusPendente);
        solicitacaoRepository.save(solicitacao);

        return new SolicitacaoResponse(
                solicitacao.getId(),
                servico.getTitulo(),
                servico.getPerfilPrestador().getNomeRazaoSocial(),
                solicitacao.getWhatsappPrestadorSnapshot(),
                solicitacao.getDataSolicitacao(),
                solicitacao.getPrazoAvaliacao(),
                solicitacao.getStatusSolicitacao().getNomeStatus()
        );
    }

    public List<SolicitacaoResponse> listarMinhasSolicitacoes(Usuario usuarioLogado) {
        PerfilCidadao perfil = perfilCidadaoRepository.findByUsuarioId(usuarioLogado.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));

        return solicitacaoRepository.findByPerfilCidadaoId(perfil.getId())
                .stream()
                .map(s -> new SolicitacaoResponse(
                        s.getId(),
                        s.getServico().getTitulo(),
                        s.getServico().getPerfilPrestador().getNomeRazaoSocial(),
                        s.getWhatsappPrestadorSnapshot(),
                        s.getDataSolicitacao(),
                        s.getPrazoAvaliacao(),
                        s.getStatusSolicitacao().getNomeStatus()
                ))
                .toList();
    }
}