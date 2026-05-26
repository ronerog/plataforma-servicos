package br.com.ronero.plataformaservicos.domain.usuario.dto.response;

import java.time.LocalDate;

public class UsuarioResponse {

    private Integer id;
    private String email;
    private String celular;
    private LocalDate dataCadastro;
    private Boolean ativo;

    public UsuarioResponse(Integer id, String email, String celular,
                           LocalDate dataCadastro, Boolean ativo) {
        this.id = id;
        this.email = email;
        this.celular = celular;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public Integer getId() { return id; }
    public String getEmail() { return email; }
    public String getCelular() { return celular; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public Boolean getAtivo() { return ativo; }
}