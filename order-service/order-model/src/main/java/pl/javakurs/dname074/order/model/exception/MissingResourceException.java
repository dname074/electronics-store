package pl.javakurs.dname074.order.model.exception;

public class MissingResourceException extends GlobalException {
    public MissingResourceException(String message) {
        super(message, 400);
    }
}
