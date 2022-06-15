package br.com.estevaocreations.dourcode.config.security.service;

import br.com.estevaocreations.dourcode.config.security.dto.AuthDto;
import br.com.estevaocreations.dourcode.dto.UserDto;
import br.com.estevaocreations.dourcode.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${forum.jwt.expiration}")
    String expiration;
    @Value("${forum.jwt.secret}")
    String secret;

    public AuthDto gerarToken(Authentication authentication) {
        User logged = (User)authentication.getPrincipal();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Long.parseLong(this.expiration));
        String token = Jwts.builder()
                .setIssuer("DoUrCode API")
                .setSubject(logged.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        String refresh_token = "";

        return new AuthDto(token, "Bearer", refresh_token, new UserDto(logged));
    }

    public boolean isTokenValid(String token) {
        //o método paseClaimsJws retorna Exceptions, por isso está dentro do try
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());

    }
}
