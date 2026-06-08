package pl.javakurs.dname074.order_service;

import org.springframework.data.domain.Page;
import pl.javakurs.dname074.order.dto.PageDto;
import pl.javakurs.dname074.order.model.PagePojo;

import java.util.function.Function;

public interface PageMapper {
    <T,R> PageDto<R> toDto(PagePojo<T> page, Function<T, R> mapper);
    <T,R> PagePojo<R> dtoToPojo(PageDto<T> pageDto, Function<T, R> mapper);
    <T,R> PagePojo<R> entityToPojo(Page<T> page, Function<T, R> mapper);
}
