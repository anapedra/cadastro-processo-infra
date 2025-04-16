package com.anapedra.cadastros_usuario.entities;

import com.anapedra.cadastros_usuario.valid.Validator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @CPF(message = "Entre com um cpf válido")
    @Column(unique = true )
    private String cpf;
    @Column(unique = true )
    private String email;
    private String senha;
    private String tel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    @Transient
    private Integer idade;

    public User() {}

    public User(Long id, String nome, String cpf, String email, String senha, String tel, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.setCpf(cpf);  
        this.setEmail(email);
        this.setSenha(senha);
        this.setTel(tel);
        this.dataNascimento = dataNascimento;
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
        this.cpf = Validator.validateAndFormatCPF(cpf); 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Validator.validateEmail(email); 
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        Validator.validatePassword(senha); 
        this.senha = senha;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = Validator.validateAndFormatPhone(tel);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        if (dataNascimento != null) {
            return Period.between(dataNascimento, LocalDate.now()).getYears();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
