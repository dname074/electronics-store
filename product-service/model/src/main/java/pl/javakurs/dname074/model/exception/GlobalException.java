package pl.javakurs.dname074.model.exception;

import lombok.Getter;
import pl.javakurs.dname074.model.HttpStatus;

@Getter
public class GlobalException extends RuntimeException {
    private final HttpStatus status;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
