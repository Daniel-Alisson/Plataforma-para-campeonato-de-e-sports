package com.daniel.tde_backend.exceptions;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String msg) {
        super(msg);
    }
}
