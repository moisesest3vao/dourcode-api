package br.com.estevaocreations.dourcode.controller;

import br.com.estevaocreations.dourcode.dto.UserDto;
import br.com.estevaocreations.dourcode.form.UserForm;
import br.com.estevaocreations.dourcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserForm userForm){
        UserDto userDto = userService.create(userForm);
        return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> createUser(@PathVariable(value = "id") Long id){
        UserDto userDto = userService.getOne(id);
        return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> getSelf(){
        UserDto userDto = userService.getSelf();
        return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.badRequest().build();
    }
}
