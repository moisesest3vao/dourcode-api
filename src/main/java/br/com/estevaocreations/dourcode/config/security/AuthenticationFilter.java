package br.com.estevaocreations.dourcode.config.security;

import br.com.estevaocreations.dourcode.config.security.service.TokenService;
import br.com.estevaocreations.dourcode.model.User;
import br.com.estevaocreations.dourcode.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter extends OncePerRequestFilter {
    TokenService tokenService;
    UserRepository userRepository;

    public AuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        super();
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);

        if(tokenService.isTokenValid(token)) {
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        Long id = tokenService.getIdUsuario(token);
        Optional<User> optional = this.userRepository.findById(id);
        if(optional.isPresent()) {
            User user = optional.get();
            UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token==null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.replace("Bearer ", "");
    }
}
