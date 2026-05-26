package br.com.ronero.plataformaservicos.domain.ocorrencia.dto.response;

import br.com.ronero.plataformaservicos.shared.enums.Role;
import br.com.ronero.plataformaservicos.shared.enums.TipoOcorrencia;

import java.time.LocalDate;

public class OcorrenciaResponse {

    private Integer id;
    private Integer idUsuario;
    private TipoOcorrencia tipo;
    private Role afetaRole;
    private String descricao;
    private LocalDate dataOcorrencia;
    private LocalDate suspensaoAte;

    public OcorrenciaResponse(Integer id, Integer idUsuario, TipoOcorrencia tipo,
                              Role afetaRole, String descricao,
                              LocalDate dataOcorrencia, LocalDate suspensaoAte) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.afetaRole = afetaRole;
        this.descricao = descricao;
        this.dataOcorrencia = dataOcorrencia;
        this.suspensaoAte = suspensaoAte;
    }

    public Integer getId() { return id; }
    public Integer getIdUsuario() { return idUsuario; }
    public TipoOcorrencia getTipo() { return tipo; }
    public Role getAfetaRole() { return afetaRole; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataOcorrencia() { return dataOcorrencia; }
    public LocalDate getSuspensaoAte() { return suspensaoAte; }
}