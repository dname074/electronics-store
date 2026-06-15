package pl.javakurs.dname074.invoice_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.javakurs.dname074.invoice.dto.ExceptionResponseDto;
import pl.javakurs.dname074.invoice.model.exception.GlobalException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(GlobalException exception) {
        log.error("Exception has occurred, message: {}", exception.getMessage());
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode());
        return ResponseEntity.status(status).body(new ExceptionResponseDto(status.value(), exception.getMessage()));
    }
}
