package com.anapedra.cadastros_usuario.componet;

import com.anapedra.cadastros_usuario.entities.User;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile({"dev", "test"})
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;


    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                    new User(null, "Ana", "12345678900", "ana@example.com", "Senha123", "71999999999", LocalDate.of(1990, 1, 1)),
                    new User(null, "João", "11122233344", "joao@example.com", "Senha123", "71988888888", LocalDate.of(1985, 5, 15)),
                    new User(null, "Carlos", "22233344455", "carlos@example.com", "Senha123", "71998887766", LocalDate.of(1992, 7, 12)),
                    new User(null, "Marina", "33344455566", "marina@example.com", "Senha123", "71997776655", LocalDate.of(1988, 11, 23)),
                    new User(null, "Lucas", "44455566677", "lucas@example.com", "Senha123", "71996665544", LocalDate.of(1995, 3, 9)),
                    new User(null, "Juliana", "55566677788", "juliana@example.com", "Senha123", "71995554433", LocalDate.of(1993, 9, 30)),
                    new User(null, "Rafael", "66677788899", "rafael@example.com", "Senha123", "71994443322", LocalDate.of(1987, 6, 5)),
                    new User(null, "Camila", "77788899900", "camila@example.com", "Senha123", "71993332211", LocalDate.of(1991, 4, 18)),
                    new User(null, "Bruno", "88899900011", "bruno@example.com", "Senha123", "71992221100", LocalDate.of(1996, 12, 2)),
                    new User(null, "Fernanda", "99900011122", "fernanda@example.com", "Senha123", "71991110099", LocalDate.of(1994, 10, 14)),
                    new User(null, "Thiago", "00011122233", "thiago@example.com", "Senha123", "71990009988", LocalDate.of(1989, 2, 27))
            );


        }
    }
}
