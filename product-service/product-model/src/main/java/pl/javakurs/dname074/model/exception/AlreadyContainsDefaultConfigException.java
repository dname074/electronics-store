package pl.javakurs.dname074.model.exception;

public class AlreadyContainsDefaultConfigException extends GlobalException {
    public AlreadyContainsDefaultConfigException(String message) {
        super(message, 409);
    }
}
