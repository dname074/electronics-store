package pl.javakurs.dname074.invoice.model.exception;

public class ExternalClientException extends GlobalException {
    public ExternalClientException(String message, Integer code) {
        super(message, code);
    }
}
