package br.com.ronero.plataformaservicos.domain.auth.dto.response;

import java.util.List;

public class LoginResponse {

    private String token;
    private String email;
    private List<String> roles;

    public LoginResponse(String token, String email, List<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public List<String> getRoles() { return roles; }
}