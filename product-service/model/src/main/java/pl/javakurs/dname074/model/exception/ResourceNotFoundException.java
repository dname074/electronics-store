package pl.javakurs.dname074.model.exception;

import pl.javakurs.dname074.model.HttpStatus;

public class ResourceNotFoundException extends GlobalException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
