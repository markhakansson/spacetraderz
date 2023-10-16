package com.markhakansson.spacetraderz.app.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String s) {
        super(s);
    }
}
