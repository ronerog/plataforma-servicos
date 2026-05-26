package br.com.ronero.plataformaservicos.domain.usuario.dto.response;

import br.com.ronero.plataformaservicos.shared.enums.Genero;
import java.time.LocalDate;

public class PerfilCidadaoResponse {

    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Genero genero;
    private String fotoPerfil;

    public PerfilCidadaoResponse(Integer id, String nome, String cpf,
                                 LocalDate dataNascimento, Genero genero,
                                 String fotoPerfil) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Genero getGenero() { return genero; }
    public String getFotoPerfil() { return fotoPerfil; }
}