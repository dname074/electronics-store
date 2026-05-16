package pl.javakurs.dname074.model.exception;

import pl.javakurs.dname074.model.HttpStatus;

public class ResourceAlreadyExistsException extends GlobalException {
    public ResourceAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
