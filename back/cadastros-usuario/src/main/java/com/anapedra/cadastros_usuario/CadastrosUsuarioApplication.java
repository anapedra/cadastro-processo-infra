package com.anapedra.cadastros_usuario;

import com.anapedra.cadastros_usuario.entities.User;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CadastrosUsuarioApplication implements CommandLineRunner{

	private final UserRepository userRepository;
    public CadastrosUsuarioApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CadastrosUsuarioApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
      List<User> users = new ArrayList<>();
		 DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

// Uso:
		User u1 = new User(1L, "Ana Santana", "01589021576", "ana@gmail.com", "P@dro2310", "(61)99334-7731", LocalDate.parse("10-10-1989", DATE_FORMATTER));



	}
}
//sudo -u postgres psql
/*

\c cadastro_user_db;

DROP TABLE IF EXISTS tb_user;
4. Recriar a tabela tb_user

\d tb_user
DROP DATABASE IF EXISTS cadastro_user_db;

CREATE DATABASE cadastro_user_db;


\l

\dt

\d tb_user

 */