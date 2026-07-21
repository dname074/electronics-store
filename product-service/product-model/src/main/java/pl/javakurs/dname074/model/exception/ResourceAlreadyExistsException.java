package pl.javakurs.dname074.model.exception;

public class ResourceAlreadyExistsException extends GlobalException {
    public ResourceAlreadyExistsException(String message) {
        super(message, 409);
    }
}
