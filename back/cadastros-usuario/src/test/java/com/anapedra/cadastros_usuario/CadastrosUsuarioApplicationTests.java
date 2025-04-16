package com.anapedra.cadastros_usuario;

import com.anapedra.cadastros_usuario.entities.User;
import com.anapedra.cadastros_usuario.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CadastrosUsuarioApplicationTests implements CommandLineRunner {


    @Test
	void contextLoads() {
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
//sudo -u postgres psql - \c cadastro_user_db -SELECT * FROM public.tb_user;  - DROP DATABASE cadastro_user_db;
//CREATE DATABASE cadastro_user_db;   REFRESH MATERIALIZED VIEW nome_da_sua_view;

/*
postgres=# \c cadastro_user_db
You are now connected to database "cadastro_user_db" as user "postgres".
cadastro_user_db=# DROP DATABASE cadastro_user_db;
ERROR:  cannot drop the currently open database
cadastro_user_db=# \c postgres
You are now connected to database "postgres" as user "postgres".
postgres=# DROP DATABASE cadastro_user_db;
DROP DATABASE
postgres=#


******************************************
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



@@
https://www.linkedin.com/company/grupo-hdi-brasil/about/
 */