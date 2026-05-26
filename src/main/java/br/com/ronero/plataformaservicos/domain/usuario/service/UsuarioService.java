package br.com.ronero.plataformaservicos.domain.usuario.service;

import br.com.ronero.plataformaservicos.domain.usuario.dto.request.CadastroCidadaoRequest;
import br.com.ronero.plataformaservicos.domain.usuario.dto.request.CadastroPrestadorRequest;
import br.com.ronero.plataformaservicos.domain.usuario.dto.response.PerfilCidadaoResponse;
import br.com.ronero.plataformaservicos.domain.usuario.dto.response.PerfilPrestadorResponse;
import br.com.ronero.plataformaservicos.domain.usuario.entity.*;
import br.com.ronero.plataformaservicos.domain.usuario.repository.*;
import br.com.ronero.plataformaservicos.shared.enums.Role;
import br.com.ronero.plataformaservicos.shared.exception.NegocioException;
import br.com.ronero.plataformaservicos.shared.exception.RecursoNaoEncontradoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilCidadaoRepository perfilCidadaoRepository;
    private final PerfilPrestadorRepository perfilPrestadorRepository;
    private final EnderecoRepository enderecoRepository;
    private final StatusPrestadorRepository statusPrestadorRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PerfilCidadaoRepository perfilCidadaoRepository,
                          PerfilPrestadorRepository perfilPrestadorRepository,
                          EnderecoRepository enderecoRepository,
                          StatusPrestadorRepository statusPrestadorRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilCidadaoRepository = perfilCidadaoRepository;
        this.perfilPrestadorRepository = perfilPrestadorRepository;
        this.enderecoRepository = enderecoRepository;
        this.statusPrestadorRepository = statusPrestadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PerfilCidadaoResponse cadastrarCidadao(CadastroCidadaoRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new NegocioException("Email já cadastrado");
        }

        if (perfilCidadaoRepository.existsByCpf(request.getCpf())) {
            throw new NegocioException("CPF já cadastrado");
        }

        Endereco endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setMunicipio(request.getMunicipio());
        endereco.setEstado(request.getEstado());
        enderecoRepository.save(endereco);

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        usuario.setCelular(request.getCelular());

        UsuarioRole role = new UsuarioRole();
        role.setUsuario(usuario);
        role.setRole(Role.ROLE_CIDADAO);
        usuario.getRoles().add(role);

        usuarioRepository.save(usuario);

        PerfilCidadao perfil = new PerfilCidadao();
        perfil.setUsuario(usuario);
        perfil.setEndereco(endereco);
        perfil.setNome(request.getNome());
        perfil.setCpf(request.getCpf());
        perfil.setDataNascimento(request.getDataNascimento());
        perfil.setGenero(request.getGenero());
        perfilCidadaoRepository.save(perfil);

        return new PerfilCidadaoResponse(
                perfil.getId(),
                perfil.getNome(),
                perfil.getCpf(),
                perfil.getDataNascimento(),
                perfil.getGenero(),
                perfil.getFotoPerfil()
        );
    }

    public PerfilPrestadorResponse cadastrarPrestador(CadastroPrestadorRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new NegocioException("Email já cadastrado");
        }

        if (perfilPrestadorRepository.existsByCpfCnpj(request.getCpfCnpj())) {
            throw new NegocioException("CPF/CNPJ já cadastrado");
        }

        Endereco endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setMunicipio(request.getMunicipio());
        endereco.setEstado(request.getEstado());
        enderecoRepository.save(endereco);

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        usuario.setCelular(request.getCelular());

        UsuarioRole role = new UsuarioRole();
        role.setUsuario(usuario);
        role.setRole(Role.ROLE_PRESTADOR);
        usuario.getRoles().add(role);

        usuarioRepository.save(usuario);

        StatusPrestador statusPendente = statusPrestadorRepository
                .findByNomeStatus("PENDENTE_APROVACAO")
                .orElseThrow(() -> new RecursoNaoEncontradoException("Status não encontrado"));

        PerfilPrestador perfil = new PerfilPrestador();
        perfil.setUsuario(usuario);
        perfil.setEndereco(endereco);
        perfil.setNomeRazaoSocial(request.getNomeRazaoSocial());
        perfil.setCpfCnpj(request.getCpfCnpj());
        perfil.setBiografia(request.getBiografia());
        perfil.setWhatsapp(request.getWhatsapp());
        perfil.setStatusPrestador(statusPendente);
        perfilPrestadorRepository.save(perfil);

        return new PerfilPrestadorResponse(
                perfil.getId(),
                perfil.getNomeRazaoSocial(),
                perfil.getCpfCnpj(),
                perfil.getBiografia(),
                perfil.getFoto(),
                perfil.getStatusPrestador().getNomeStatus()
        );
    }

    public List<PerfilCidadaoResponse> listarCidadaos() {
        return perfilCidadaoRepository.findAll()
                .stream()
                .map(p -> new PerfilCidadaoResponse(
                        p.getId(),
                        p.getNome(),
                        p.getCpf(),
                        p.getDataNascimento(),
                        p.getGenero(),
                        p.getFotoPerfil()
                ))
                .toList();
    }

    public List<PerfilPrestadorResponse> listarPrestadores() {
        return perfilPrestadorRepository.findAll()
                .stream()
                .map(p -> new PerfilPrestadorResponse(
                        p.getId(),
                        p.getNomeRazaoSocial(),
                        p.getCpfCnpj(),
                        p.getBiografia(),
                        p.getFoto(),
                        p.getStatusPrestador().getNomeStatus()
                ))
                .toList();
    }

    public PerfilCidadaoResponse buscarCidadaoPorUsuario(Integer idUsuario) {
        PerfilCidadao perfil = perfilCidadaoRepository.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));

        return new PerfilCidadaoResponse(
                perfil.getId(),
                perfil.getNome(),
                perfil.getCpf(),
                perfil.getDataNascimento(),
                perfil.getGenero(),
                perfil.getFotoPerfil()
        );
    }
}