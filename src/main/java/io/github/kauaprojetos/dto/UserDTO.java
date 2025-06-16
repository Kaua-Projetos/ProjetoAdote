package io.github.kauaprojetos.dto;


import io.github.kauaprojetos.exception.ErrorException;

public record UserDTO(String nome, String email, String senha, String cpf) {

    public void cpfErrorNumber(){
        if (cpf.length() != 11){
            throw new ErrorException("CPF invalido!");
        }
    }
    public void passwordShort(){
        if (senha == null) {
            throw new ErrorException("Senha não pode ser nula!");
        } else if (senha.length() < 8){
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
