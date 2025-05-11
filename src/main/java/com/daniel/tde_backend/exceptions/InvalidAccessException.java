package com.daniel.tde_backend.exceptions;

public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException(String msg) {
        super(msg);
    }
}
