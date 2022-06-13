package br.com.estevaocreations.dourcode.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_USER("ROLE_USER"),
    ROLE_MOD("ROLE_MOD"),
    ROLE_ADMIN("ROLE_ADMIN");
    private String role;

    Role(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
