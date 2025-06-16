package io.github.kauaprojetos.controller;

import io.github.kauaprojetos.dto.LoginDTO;
import io.github.kauaprojetos.dto.UserDTO;
import io.github.kauaprojetos.model.UserModel;
import io.github.kauaprojetos.repository.UserRepository;
import io.github.kauaprojetos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<UserModel> listUser(){
        return userService.listUser();
    }

    @PostMapping("/save")
    public ResponseEntity<?> salvar(@RequestBody UserDTO dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok(Map.of("message", "Usuário cadastrado com sucesso!"));
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        UserModel user = userService.autenticar(dto.email(), dto.senha());
        return ResponseEntity.ok(Map.of(
                "message", "Login realizado com sucesso!",
                "nome", user.getName(),
                "email", user.getEmail(),
                "cpf", user.getCpf()
        ));
    }

}
