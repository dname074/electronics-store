package pl.javakurs.dname074.bff.model.exception;

public class ExternalClientException extends GlobalException {
    public ExternalClientException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
