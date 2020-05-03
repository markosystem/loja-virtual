package com.capitani.brasilprev.lojavirtual.controller;

import com.capitani.brasilprev.lojavirtual.model.representation.AuthFormDto;
import com.capitani.brasilprev.lojavirtual.model.representation.TokenDto;
import com.capitani.brasilprev.lojavirtual.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authentication(@RequestBody @Valid AuthFormDto form) {
        UsernamePasswordAuthenticationToken data = form.converter();
        try {
            Authentication authentication = authManager.authenticate(data);
            String token = tokenService.gerarateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
