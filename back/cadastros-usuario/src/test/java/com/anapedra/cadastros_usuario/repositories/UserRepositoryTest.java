package com.anapedra.cadastros_usuario.repositories;

import com.anapedra.cadastros_usuario.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void saveShouldPersistUserWhenDataIsValid() {
        User user = new User();
        user.setNome("Ana");
        user.setCpf("015.890.215-76");
        user.setEmail("ana.test@example.com");
        user.setSenha("Senha@123");
        user.setTel("31997502148");
        user.setDataNascimento(LocalDate.of(1990, 1, 1));

        User saved = repository.save(user);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNome()).isEqualTo("Ana");
    }

    @Test
    void findByEmailShouldReturnUserWhenEmailExists() {
        User user = new User();
        user.setNome("Ana");
        user.setCpf("987.654.321-00");
        user.setEmail("test.unique@example.com");
        user.setSenha("Senha@123");
        user.setTel("31999999999");
        user.setDataNascimento(LocalDate.of(1995, 5, 5));

        repository.save(user);

        Optional<User> result = repository.findByEmail("test.unique@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getNome()).isEqualTo("Ana");
    }
}
