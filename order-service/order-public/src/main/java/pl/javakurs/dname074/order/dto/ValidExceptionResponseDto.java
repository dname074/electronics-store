package pl.javakurs.dname074.order.dto;

import java.util.List;

public record ValidExceptionResponseDto(Integer statusCode, List<String> messages) {
}
