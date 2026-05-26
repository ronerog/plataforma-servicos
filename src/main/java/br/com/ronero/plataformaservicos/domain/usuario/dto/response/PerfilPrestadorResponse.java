package br.com.ronero.plataformaservicos.domain.usuario.dto.response;

public class PerfilPrestadorResponse {

    private Integer id;
    private String nomeRazaoSocial;
    private String cpfCnpj;
    private String biografia;
    private String foto;
    private String statusPrestador;

    public PerfilPrestadorResponse(Integer id, String nomeRazaoSocial, String cpfCnpj,
                                   String biografia, String foto, String statusPrestador) {
        this.id = id;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.biografia = biografia;
        this.foto = foto;
        this.statusPrestador = statusPrestador;
    }

    public Integer getId() { return id; }
    public String getNomeRazaoSocial() { return nomeRazaoSocial; }
    public String getCpfCnpj() { return cpfCnpj; }
    public String getBiografia() { return biografia; }
    public String getFoto() { return foto; }
    public String getStatusPrestador() { return statusPrestador; }
}