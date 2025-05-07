package com.daniel.tde_backend.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String msg) {
        super(msg);
    }
}
