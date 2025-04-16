package com.anapedra.cadastros_usuario.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    // Validação e formatação do CPF
    public static String validateAndFormatCPF(String cpf) throws IllegalArgumentException {
        String cpfRegex = "^\\d{3}[-.]?\\d{3}[-.]?\\d{3}[-.]?\\d{2}$";
        if (cpf == null || !cpf.matches(cpfRegex)) {
            throw new IllegalArgumentException("CPF inválido. Formatos aceitos: 015.890.215.76, 015-890-215-76, 01589021576");
        }

        // Remover caracteres não numéricos e formatar para XXX.XXX.XXX-XX
        cpf = cpf.replaceAll("[^\\d]", "");
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
    }

    // Validação do e-mail
    public static void validateEmail(String email) throws IllegalArgumentException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    public static String validateAndFormatPhone(String tel) {
        if (tel == null) {
            throw new IllegalArgumentException("Telefone inválido: valor nulo.");
        }

        // Remove todos os caracteres que não são dígitos
        String digits = tel.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos (DDD + número com 9 dígitos)
        if (!digits.matches("\\d{11}")) {
            throw new IllegalArgumentException("Telefone inválido. Insira um número com DDD e 9 dígitos, ex: 61993347731");
        }

        // Formata para o padrão desejado
        String ddd = digits.substring(0, 2);
        String prefixo = digits.substring(2, 7);
        String sufixo = digits.substring(7);

        return String.format("(%s)%s-%s", ddd, prefixo, sufixo);
    }


    // Validação de senha
    public static void validatePassword(String senha) throws IllegalArgumentException {
        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra maiúscula.");
        }
        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra minúscula.");
        }
        if (!senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um número.");
        }
        if (!senha.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um caractere especial.");
        }
    }
}

/*
******************

********************
@Configuration
public class WebConfig {

	@Value("${cors.origins}")
	private String corsOrigins;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins(corsOrigins);
			}
		};
	}
 */
