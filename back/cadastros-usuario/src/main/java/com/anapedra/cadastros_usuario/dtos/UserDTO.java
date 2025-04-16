package com.anapedra.cadastros_usuario.dtos;

import com.anapedra.cadastros_usuario.entities.User;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String nome;
    @CPF
    private String cpf;
    @Email
    private String email;
    private String tel;
    private LocalDate dataNascimento;
    private Integer idade;

    public UserDTO() {}

    public UserDTO(Long id, String nome, String cpf, String email, String tel, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tel = tel;
        this.dataNascimento = dataNascimento;

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.tel = user.getTel();
        this.dataNascimento = user.getDataNascimento();
        this.idade = user.getIdade();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }


}
