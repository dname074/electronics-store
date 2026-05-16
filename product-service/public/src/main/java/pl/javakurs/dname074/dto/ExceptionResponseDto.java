package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.HttpStatus;

public record ExceptionResponseDto(HttpStatus status, String message) {
}
