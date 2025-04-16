package com.anapedra.cadastros_usuario.services;

import com.anapedra.cadastros_usuario.dtos.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO findByEmail(String email);

    UserDTO findByCpf(String cpf);

    UserDTO insert(UserDTO dto);

    UserDTO update(Long id, UserDTO dto);

    void delete(Long id);
}
