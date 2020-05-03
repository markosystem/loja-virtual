package com.capitani.brasilprev.lojavirtual.service;

import com.capitani.brasilprev.lojavirtual.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${lojavirtual.jwt.expiration}")
    private String expiration;

    @Value("${lojavirtual.jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    public String gerarateToken(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date hoje = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoje);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date dataExpiration = new Date(calendar.getTime().getTime());
        return Jwts.builder().setIssuer("API REST Loja Virtual")
                .setSubject(logged.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}