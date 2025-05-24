package com.daniel.tde_backend.exceptions;

public class InsufficientPlayersException extends RuntimeException {

    public InsufficientPlayersException(String msg) {
        super(msg);
    }
}
