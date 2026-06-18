package pl.javakurs.dname074.electronics_store_bff;

import pl.javakurs.dname074.bff.dto.PageDto;
import pl.javakurs.dname074.bff.model.PagePojo;

import java.util.function.Function;

public interface PageMapper {
    <T,R> PageDto<R> toDto(PagePojo<T> page, Function<T, R> mapper);
    <T,R> PagePojo<R> dtoToPojo(PageDto<T> pageDto, Function<T, R> mapper);
}
