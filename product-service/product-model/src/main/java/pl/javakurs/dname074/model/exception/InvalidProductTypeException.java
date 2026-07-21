package pl.javakurs.dname074.model.exception;

public class InvalidProductTypeException extends GlobalException {
    public InvalidProductTypeException(String message) {
        super(message, 409);
    }
}
