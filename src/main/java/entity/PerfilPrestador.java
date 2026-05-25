package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil_prestador")
public class PerfilPrestador extends Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_prestador")
    private Integer id;

    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 20)
    private String cpfCnpj;

    @Column(name = "nome_razao_social", nullable = false, length = 255)
    private String nomeRazaoSocial;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(length = 500)
    private String foto;

    @Column(nullable = false, length = 20)
    private String whatsapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_prestador", nullable = false)
    private StatusPrestador statusPrestador;

    public PerfilPrestador() {
    }

    public PerfilPrestador(Integer id, String cpfCnpj, String nomeRazaoSocial,
                           String biografia, String foto, String whatsapp,
                           StatusPrestador statusPrestador,
                           Usuario usuario, Endereco endereco) {

        super(usuario, endereco);

        this.id = id;
        this.cpfCnpj = cpfCnpj;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.biografia = biografia;
        this.foto = foto;
        this.whatsapp = whatsapp;
        this.statusPrestador = statusPrestador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public StatusPrestador getStatusPrestador() {
        return statusPrestador;
    }

    public void setStatusPrestador(StatusPrestador statusPrestador) {
        this.statusPrestador = statusPrestador;
    }
}