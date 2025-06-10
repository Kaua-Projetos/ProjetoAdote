package io.github.kauaprojetos.dto;


import io.github.kauaprojetos.exception.ErrorException;
import io.github.kauaprojetos.security.WebSecurity;
import org.springframework.beans.factory.annotation.Autowired;

public record UserDTO(String nome, String email, String senha, String cpf) {

    public void cpfErrorNumber(){
        if (cpf.length() != 11){
            throw new ErrorException("CPF invalido!");
        }
    }
    public void passwordShort(){
        if (senha.length() < 8){
            throw new ErrorException("Senha muito curta!");
        }
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String senha() {
        return senha;
    }

    @Override
    public String cpf() {
        return cpf;
    }
}
