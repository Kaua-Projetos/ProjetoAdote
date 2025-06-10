package io.github.kauaprojetos.security;

import io.github.kauaprojetos.model.UserModel;
import io.github.kauaprojetos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = service.obterPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado!"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toArray(new String[0]))
                .build();


    }
}
