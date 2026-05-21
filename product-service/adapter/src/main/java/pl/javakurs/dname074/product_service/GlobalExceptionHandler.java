package pl.javakurs.dname074.product_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.javakurs.dname074.dto.ExceptionResponseDto;
import pl.javakurs.dname074.model.exception.GlobalException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(GlobalException exception) {
        log.error("Exception has occurred, message: {}", exception.getMessage());
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode());
        return ResponseEntity.status(status).body(new ExceptionResponseDto(status.value(), exception.getMessage()));
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
    public ResponseEntity<ExceptionResponseDto> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("Validation exception has occurred, message: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(400, message));
    }
}
