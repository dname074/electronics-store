package pl.javakurs.dname074.cart.model.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final Integer statusCode;

    public GlobalException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
