package pl.javakurs.dname074.invoice_service;

import org.springframework.data.domain.Page;
import pl.javakurs.dname074.invoice.dto.PageDto;
import pl.javakurs.dname074.invoice.model.PagePojo;

import java.util.function.Function;

public interface PageMapper {
    <T,R> PageDto<R> toDto(PagePojo<T> page, Function<T, R> mapper);
    <T,R> PagePojo<R> dtoToPojo(PageDto<T> pageDto, Function<T, R> mapper);
    <T,R> PagePojo<R> entityToPojo(Page<T> page, Function<T, R> mapper);
}
