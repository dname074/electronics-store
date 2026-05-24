package pl.javakurs.dname074.dto;

import java.util.List;

public record ValidExceptionResponseDto(Integer statusCode, List<String> messages) {
}
