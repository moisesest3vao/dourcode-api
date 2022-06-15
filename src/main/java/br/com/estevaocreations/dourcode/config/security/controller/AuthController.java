package br.com.estevaocreations.dourcode.config.security.controller;

import br.com.estevaocreations.dourcode.config.security.dto.AuthDto;
import br.com.estevaocreations.dourcode.config.security.form.LoginForm;
import br.com.estevaocreations.dourcode.config.security.service.AuthenticationService;
import br.com.estevaocreations.dourcode.config.security.service.TokenService;
import br.com.estevaocreations.dourcode.dto.UserDto;
import br.com.estevaocreations.dourcode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationService authService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken usernamePassword = form.converter();
        try {
            Authentication authentication = authManager.authenticate(usernamePassword);
            AuthDto auth = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(auth);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
