package com.capitani.brasilprev.lojavirtual.model.representation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AuthFormDto {
    @NotBlank(message = "O Login do usuário deve ser informado!")
    private String login;
    @NotBlank(message = "A Senha do usuário deve ser informada!")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
