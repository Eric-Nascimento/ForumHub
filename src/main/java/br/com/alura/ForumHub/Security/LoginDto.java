package br.com.alura.ForumHub.Security;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}