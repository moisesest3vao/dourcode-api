package br.com.estevaocreations.dourcode.service;

import br.com.estevaocreations.dourcode.dto.UserDto;
import br.com.estevaocreations.dourcode.form.UserForm;
import br.com.estevaocreations.dourcode.model.User;
import br.com.estevaocreations.dourcode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserDto create(UserForm userForm){
        User user = new User(userForm);
        user = this.userRepository.save(user);
        return new UserDto(user);
    }

    public UserDto getOne(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        return user != null ? new UserDto(user) : null;
    }

    public UserDto getSelf() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        return  user != null ? new UserDto(user) : null;
    }
}
