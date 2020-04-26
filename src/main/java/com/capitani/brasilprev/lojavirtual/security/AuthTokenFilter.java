package com.capitani.brasilprev.lojavirtual.security;

import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.repository.UserRepository;
import com.capitani.brasilprev.lojavirtual.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    private UserRepository userRepository;

    public AuthTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid)
            authUser(token);
        filterChain.doFilter(request, response);
    }

    private void authUser(String token) {
        Long idUser = tokenService.getIdUser(token);
        User user = userRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
            return null;
        return token.substring(7, token.length());
    }
}
