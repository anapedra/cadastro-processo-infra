package com.anapedra.cadastros_usuario.services.exceptions;


public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
