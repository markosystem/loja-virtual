package com.capitani.brasilprev.lojavirtual.model.representation;

import javax.validation.constraints.NotBlank;

public class TokenDto {
    @NotBlank(message = "O Token do usuário deve ser informado!")
    private String token;
    @NotBlank(message = "O Tipo do Token do usuário deve ser informado!")
    private String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
