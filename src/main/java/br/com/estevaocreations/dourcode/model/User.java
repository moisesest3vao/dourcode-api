package br.com.estevaocreations.dourcode.model;

import br.com.estevaocreations.dourcode.config.security.service.AuthenticationService;
import br.com.estevaocreations.dourcode.enums.Role;
import br.com.estevaocreations.dourcode.form.UserForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserForm userForm) {
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
        this.firstName = userForm.getFirstName();
        this.lastName = userForm.getLastName();
        this.fullName = userForm.getFullName();
        this.role = Role.ROLE_USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthenticationService.mapToGrantedAuthorities(this.role);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
