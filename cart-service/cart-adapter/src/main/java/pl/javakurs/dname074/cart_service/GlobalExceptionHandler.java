package pl.javakurs.dname074.cart_service;

import feign.RetryableException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.javakurs.dname074.cart.dto.ExceptionResponseDto;
import pl.javakurs.dname074.cart.dto.ValidExceptionResponseDto;
import pl.javakurs.dname074.cart.model.exception.GlobalException;

import java.util.List;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(GlobalException exception) {
        log.error("Exception has occured, message: {}", exception.getMessage());
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode());
        return ResponseEntity.status(status).body(new ExceptionResponseDto(exception.getStatusCode(), exception.getMessage()));
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ExceptionResponseDto> handleBadRequests(Exception exception) {
        log.error("Exception has occurred, because of bad request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(400, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptionResponseDto> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        log.error("Validation exception has occurred, messages: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidExceptionResponseDto(400, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidExceptionResponseDto> handleConstraintViolationException(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .toList();
        log.error("Validation exception has occured, messages: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidExceptionResponseDto(400, errors));
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ExceptionResponseDto> handleRetryableException(RetryableException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ExceptionResponseDto(500, "External service failed after retries"));
    }
}
