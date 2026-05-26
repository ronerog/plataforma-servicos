package br.com.ronero.plataformaservicos.domain.servico.service;

import br.com.ronero.plataformaservicos.domain.servico.dto.request.CriarCategoriaRequest;
import br.com.ronero.plataformaservicos.domain.servico.dto.request.CriarServicoRequest;
import br.com.ronero.plataformaservicos.domain.servico.dto.response.CategoriaResponse;
import br.com.ronero.plataformaservicos.domain.servico.dto.response.ServicoResponse;
import br.com.ronero.plataformaservicos.domain.servico.entity.Categoria;
import br.com.ronero.plataformaservicos.domain.servico.entity.Servico;
import br.com.ronero.plataformaservicos.domain.servico.entity.StatusServico;
import br.com.ronero.plataformaservicos.domain.servico.repository.CategoriaRepository;
import br.com.ronero.plataformaservicos.domain.servico.repository.ServicoRepository;
import br.com.ronero.plataformaservicos.domain.servico.repository.StatusServicoRepository;
import br.com.ronero.plataformaservicos.domain.usuario.entity.PerfilPrestador;
import br.com.ronero.plataformaservicos.domain.usuario.entity.Usuario;
import br.com.ronero.plataformaservicos.domain.usuario.repository.PerfilPrestadorRepository;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final CategoriaRepository categoriaRepository;
    private final StatusServicoRepository statusServicoRepository;
    private final PerfilPrestadorRepository perfilPrestadorRepository;

    public ServicoService(ServicoRepository servicoRepository,
                          CategoriaRepository categoriaRepository,
                          StatusServicoRepository statusServicoRepository,
                          PerfilPrestadorRepository perfilPrestadorRepository) {
        this.servicoRepository = servicoRepository;
        this.categoriaRepository = categoriaRepository;
        this.statusServicoRepository = statusServicoRepository;
        this.perfilPrestadorRepository = perfilPrestadorRepository;
    }

    public CategoriaResponse criarCategoria(CriarCategoriaRequest request) {
        if (categoriaRepository.existsByNomeCategoria(request.getNomeCategoria())) {
            throw new NegocioException("Categoria já existe");
        }

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(request.getNomeCategoria());
        categoria.setAtivo(true);
        categoriaRepository.save(categoria);

        return new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria(), categoria.getAtivo());
    }

    public List<CategoriaResponse> listarCategorias() {
        return categoriaRepository.findByAtivoTrue()
                .stream()
                .map(c -> new CategoriaResponse(c.getId(), c.getNomeCategoria(), c.getAtivo()))
                .toList();
    }

    public ServicoResponse criarServico(CriarServicoRequest request, Usuario usuarioLogado) {
        PerfilPrestador perfil = perfilPrestadorRepository.findByUsuarioId(usuarioLogado.getId())
                .orElseThrow(() -> new NegocioException("Usuário não possui perfil de prestador"));

        if (!perfil.getStatusPrestador().getNomeStatus().equals("APROVADO")) {
            throw new NegocioException("Prestador não está aprovado para publicar serviços");
        }

        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

        if (!categoria.getAtivo()) {
            throw new NegocioException("Categoria inativa");
        }

        StatusServico statusRascunho = statusServicoRepository.findByNomeStatus("RASCUNHO")
                .orElseThrow(() -> new RecursoNaoEncontradoException("Status não encontrado"));

        Servico servico = new Servico();
        servico.setPerfilPrestador(perfil);
        servico.setCategoria(categoria);
        servico.setTitulo(request.getTitulo());
        servico.setDescricao(request.getDescricao());
        servico.setStatusServico(statusRascunho);
        servicoRepository.save(servico);

        return new ServicoResponse(
                servico.getId(),
                servico.getTitulo(),
                servico.getDescricao(),
                servico.getCategoria().getNomeCategoria(),
                servico.getStatusServico().getNomeStatus(),
                perfil.getNomeRazaoSocial()
        );
    }

    public List<ServicoResponse> listarServicosPublicados() {
        return servicoRepository.findByStatusServicoNomeStatus("PUBLICADO")
                .stream()
                .map(s -> new ServicoResponse(
                        s.getId(),
                        s.getTitulo(),
                        s.getDescricao(),
                        s.getCategoria().getNomeCategoria(),
                        s.getStatusServico().getNomeStatus(),
                        s.getPerfilPrestador().getNomeRazaoSocial()
                ))
                .toList();
    }
}