package pl.javakurs.dname074.model.exception;

public class ResourceNotFoundException extends GlobalException {
    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
