package pl.javakurs.dname074.invoice.dto;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        int totalPages,
        long totalElements,
        int pageNumber,
        int pageSize
) {
}
