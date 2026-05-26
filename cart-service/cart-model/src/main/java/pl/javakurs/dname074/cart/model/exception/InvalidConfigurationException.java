package pl.javakurs.dname074.cart.model.exception;

public class InvalidConfigurationException extends GlobalException {
    public InvalidConfigurationException(String message) {
        super(message, 400);
    }
}
