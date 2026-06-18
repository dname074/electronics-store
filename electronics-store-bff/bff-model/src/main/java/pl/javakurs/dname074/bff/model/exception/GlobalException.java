package pl.javakurs.dname074.bff.model.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final Integer statusCode;

    public GlobalException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
