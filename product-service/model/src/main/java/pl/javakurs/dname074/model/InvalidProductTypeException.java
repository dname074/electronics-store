package pl.javakurs.dname074.model;

import pl.javakurs.dname074.model.exception.GlobalException;

public class InvalidProductTypeException extends GlobalException {
    public InvalidProductTypeException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
