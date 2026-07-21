package pl.javakurs.dname074.cart.model.exception;

public class ResourceNotFoundException extends GlobalException {
    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
