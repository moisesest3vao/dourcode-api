package br.com.estevaocreations.dourcode.config.security.dto;

import br.com.estevaocreations.dourcode.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    String token;
    String type;
    String refresh_token;
    UserDto user;
}
