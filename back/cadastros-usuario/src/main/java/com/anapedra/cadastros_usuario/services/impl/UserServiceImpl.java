package com.anapedra.cadastros_usuario.services.impl;

import com.anapedra.cadastros_usuario.dtos.UserDTO;
import com.anapedra.cadastros_usuario.entities.User;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import com.anapedra.cadastros_usuario.services.UserService;
import com.anapedra.cadastros_usuario.services.exceptions.DatabaseException;
import com.anapedra.cadastros_usuario.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(UserDTO::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("User not found for id " + id));
        return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> obj = userRepository.findByEmail(email);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("User not found for email " + email));
        return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findByCpf(String cpf) {
        Optional<User> obj = userRepository.findByCpf(cpf);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("User not found for CPF " + cpf));
        return new UserDTO(entity);
    }

    @Transactional
    @Override
    public UserDTO insert(UserDTO dto) {
        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new DatabaseException("CPF: " + dto.getCpf() +" já cadastrado" );
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DatabaseException("Email: " + dto.getEmail() + " já cadastrado");
        }
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    @Override
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found for id " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setTel(dto.getTel());
        entity.setDataNascimento(dto.getDataNascimento());

    }
}
