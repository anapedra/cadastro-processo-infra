package com.anapedra.cadastros_usuario.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerRATest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }



    @Test
    void shouldCreateUserSuccessfully() {
        String json = """
            {
                "nome": "Teste API",
                "cpf": "892.425.630-05",
                "email": "ttai@test.com",
                "tel": "(31)99750-2148",
                "dataNascimento": "1980-01-10"
            }
            """;

        Response response = given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .post("/api/users");

        // Logs úteis em caso de falha
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Validações
        response.then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", equalTo("Teste API"));
    }
}
