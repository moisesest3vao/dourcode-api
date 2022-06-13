package br.com.estevaocreations.dourcode.form;

import br.com.estevaocreations.dourcode.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    @NotNull
    @NotBlank
    @Size(max = 50, min = 5)
    private String email;
    @NotNull
    @NotBlank
    @Size(max = 20, min = 8)
    private String password;
    @NotNull
    @NotBlank
    @Size(max = 50, min = 2)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(max = 50, min = 2)
    private String lastName;
    private String fullName;
}
