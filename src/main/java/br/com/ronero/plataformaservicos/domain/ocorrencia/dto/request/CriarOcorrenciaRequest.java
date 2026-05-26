package br.com.ronero.plataformaservicos.domain.ocorrencia.dto.request;

import br.com.ronero.plataformaservicos.shared.enums.Role;
import br.com.ronero.plataformaservicos.shared.enums.TipoOcorrencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarOcorrenciaRequest {

    @NotNull(message = "Usuário é obrigatório")
    private Integer idUsuario;

    @NotNull(message = "Tipo é obrigatório")
    private TipoOcorrencia tipo;

    @NotNull(message = "Role afetada é obrigatória")
    private Role afetaRole;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public TipoOcorrencia getTipo() { return tipo; }
    public void setTipo(TipoOcorrencia tipo) { this.tipo = tipo; }

    public Role getAfetaRole() { return afetaRole; }
    public void setAfetaRole(Role afetaRole) { this.afetaRole = afetaRole; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}