package pl.javakurs.dname074.model.exception;

import pl.javakurs.dname074.model.HttpStatus;

public class AlreadyContainsDefaultConfigException extends GlobalException {
    public AlreadyContainsDefaultConfigException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
