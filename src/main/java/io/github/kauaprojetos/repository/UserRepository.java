package io.github.kauaprojetos.repository;

import io.github.kauaprojetos.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByCpf(String cpf);

    Optional<UserModel> findByEmail(String email);

}
