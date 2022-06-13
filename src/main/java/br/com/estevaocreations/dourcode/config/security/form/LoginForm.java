package br.com.estevaocreations.dourcode.config.security.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @NotNull
    @NotBlank
    @Size(max = 50, min = 5)
    private String email;
    @NotNull
    @NotBlank
    @Size(max = 20, min = 8)
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(this.email, this.password);
        return usernamePassword;
    }
}
