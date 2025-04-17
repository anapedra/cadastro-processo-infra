package com.anapedra.cadastros_usuario.services;

import com.anapedra.cadastros_usuario.dtos.UserDTO;
import com.anapedra.cadastros_usuario.entities.User;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import com.anapedra.cadastros_usuario.services.exceptions.DatabaseException;
import com.anapedra.cadastros_usuario.services.exceptions.ResourceNotFoundException;
import com.anapedra.cadastros_usuario.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    private UserDTO dto;
    private User user;

    @BeforeEach
    void setUp() {
        dto = new UserDTO();
        dto.setNome("Ana");
        dto.setCpf("015.890.215-76");
        dto.setEmail("ana@example.com");
        dto.setTel("(31)99750-2148");
        dto.setDataNascimento(LocalDate.of(1980, 10, 23));

        user = new User();
        user.setId(1L);
        user.setNome(dto.getNome());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setTel(dto.getTel());
        user.setDataNascimento(dto.getDataNascimento());
    }

    @Test
    void insertShouldSaveUserWhenDataIsValid() {
        when(repository.existsByCpf(dto.getCpf())).thenReturn(false);
        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.save(any())).thenReturn(user);

        UserDTO result = service.insert(dto);

        assertNotNull(result);
        assertEquals(dto.getNome(), result.getNome());
        verify(repository).save(any(User.class));
    }

    @Test
    void insertShouldThrowExceptionWhenCpfExists() {
        when(repository.existsByCpf(dto.getCpf())).thenReturn(true);

        assertThrows(DatabaseException.class, () -> service.insert(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void insertShouldThrowExceptionWhenEmailExists() {
        when(repository.existsByCpf(dto.getCpf())).thenReturn(false);
        when(repository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(DatabaseException.class, () -> service.insert(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void findByIdShouldReturnUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(user.getNome(), result.getNome());
    }

    @Test
    void findByIdShouldThrowWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void deleteShouldRemoveUserWhenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.delete(1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteShouldThrowWhenUserNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
        verify(repository, never()).deleteById(any());
    }

    @Test
    void updateShouldReturnUpdatedUser() {
        when(repository.getReferenceById(1L)).thenReturn(user);
        when(repository.save(any())).thenReturn(user);

        UserDTO result = service.update(1L, dto);

        assertNotNull(result);
        assertEquals(dto.getNome(), result.getNome());
    }


} 