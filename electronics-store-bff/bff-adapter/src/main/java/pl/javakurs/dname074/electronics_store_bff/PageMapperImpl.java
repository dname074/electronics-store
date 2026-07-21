package pl.javakurs.dname074.electronics_store_bff;

import org.springframework.stereotype.Component;
import pl.javakurs.dname074.bff.dto.PageDto;
import pl.javakurs.dname074.bff.model.PagePojo;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
class PageMapperImpl implements PageMapper {
    @Override
    public <T, R> PageDto<R> toDto(PagePojo<T> page, Function<T, R> mapper) {
        return new PageDto<>(
                page.getContent().stream().map(mapper).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }

    @Override
    public <T, R> PagePojo<R> dtoToPojo(PageDto<T> pageDto, Function<T, R> mapper) {
        return new PagePojo<>(
                pageDto.content().stream().map(mapper).collect(Collectors.toList()),
                pageDto.totalPages(),
                pageDto.totalElements(),
                pageDto.pageNumber(),
                pageDto.pageSize()
        );
    }
}
