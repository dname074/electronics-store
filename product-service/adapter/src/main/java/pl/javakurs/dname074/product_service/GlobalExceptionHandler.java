package pl.javakurs.dname074.product_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.javakurs.dname074.dto.ExceptionResponseDto;
import pl.javakurs.dname074.model.exception.GlobalException;

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(GlobalException exception) {
        HttpStatus status = HttpStatus.valueOf(exception.getStatus().toString());
        return ResponseEntity.status(status).body(new ExceptionResponseDto(exception.getStatus(), exception.getMessage()));
    }
}
