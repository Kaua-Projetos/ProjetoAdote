package io.github.kauaprojetos.controller;

import io.github.kauaprojetos.dto.UserDTO;
import io.github.kauaprojetos.model.UserModel;
import io.github.kauaprojetos.repository.UserRepository;
import io.github.kauaprojetos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("save")
    public void saveUser(@RequestBody UserDTO dto){
        userService.saveUser(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

}
