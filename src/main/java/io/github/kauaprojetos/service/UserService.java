package io.github.kauaprojetos.service;

import io.github.kauaprojetos.dto.UserDTO;
import io.github.kauaprojetos.exception.ConflictException;
import io.github.kauaprojetos.exception.ErrorException;
import io.github.kauaprojetos.model.UserModel;
import io.github.kauaprojetos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDTO dto){

        UserModel user = new UserModel();
//Tratar erros
        dto.cpfErrorNumber();
        dto.passwordShort();

//Salvar no banco de dados
        user.setName(dto.nome());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.senha()));
        user.setRole(List.of("USER"));
        user.setCpf(dto.cpf());

        Optional<UserModel> cpfExists = repository.findByCpf(dto.cpf());
        Optional<UserModel> emailExists = repository.findByEmail(dto.email());

        if (cpfExists.isPresent()){
            throw new ConflictException("CPF ja existe no banco de dados!");
        } else if (emailExists.isPresent()) {
            throw new ConflictException("Email ja existe no banco de dados!");
        }
        repository.save(user);
    }

    public List<UserModel> listUser(){
        return repository.findAll();
    }

    public void deleteUser(UUID id){
        Optional<UserModel> idExist = repository.findById(id);
        if (!idExist.isPresent()){
            throw new ErrorException("Id nao encontrado!");
        }
        repository.deleteById(id);
    }
    public Optional<UserModel> obterPorEmail(String email) {
        return repository.findByEmail(email);
    }

}
