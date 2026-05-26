package br.com.ronero.plataformaservicos.domain.servico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "nome_categoria", nullable = false, unique = true, length = 100)
    private String nomeCategoria;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Categoria() {}

    public Categoria(Integer id, String nomeCategoria, Boolean ativo) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.ativo = ativo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeCategoria() { return nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}