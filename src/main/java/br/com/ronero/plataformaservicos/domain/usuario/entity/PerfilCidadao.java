package br.com.ronero.plataformaservicos.domain.usuario.entity;

import br.com.ronero.plataformaservicos.shared.enums.Genero;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "perfil_cidadao")
public class PerfilCidadao extends Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_cidadao")
    private Integer id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Genero genero;

    @Column(name = "foto_perfil", length = 500)
    private String fotoPerfil;

    public PerfilCidadao() {
    }

    public PerfilCidadao(Integer id, String cpf, String nome,
                         LocalDate dataNascimento, Genero genero,
                         String fotoPerfil, Usuario usuario, Endereco endereco) {

        super(usuario, endereco);

        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}