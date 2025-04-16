package com.anapedra.cadastros_usuario.integracao;

import com.anapedra.cadastros_usuario.dtos.UserDTO;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import com.anapedra.cadastros_usuario.services.UserService;
import com.anapedra.cadastros_usuario.services.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usa o Postgre real
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @Test
    void shouldInsertUserSuccessfully() {
        UserDTO dto = new UserDTO();
        dto.setNome("Maria");
        dto.setCpf("015.890.215-76");
        dto.setEmail("maria@example.com");
        dto.setTel("(31)99750-2148");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));

        UserDTO result = userService.insert(dto);

        assertNotNull(result.getId());
        assertEquals("Maria", result.getNome());
    }

    @Test
    void shouldThrowWhenCpfAlreadyExists() {
        // CPF válido para teste
        String validCpf = "390.533.447-05";

        UserDTO dto = new UserDTO(null, "José", validCpf, "jose@email.com", "(31)98765-4321", LocalDate.of(1985, 10, 10));
        userService.insert(dto);

        UserDTO duplicate = new UserDTO(null, "João", validCpf, "joao@email.com", "(31)98765-4321", LocalDate.of(1985, 10, 10));

        assertThrows(DatabaseException.class, () -> userService.insert(duplicate));
    }
}
