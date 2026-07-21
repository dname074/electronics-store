package pl.javakurs.dname074.cart.model.exception;

public class DuplicateConfigurationException extends GlobalException {
    public DuplicateConfigurationException(String message) {
        super(message, 400);
    }
}
