package pl.javakurs.dname074.invoice_service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.dto.PageDto;
import pl.javakurs.dname074.invoice.model.PagePojo;

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

    @Override
    public <T, R> PagePojo<R> entityToPojo(Page<T> page, Function<T, R> mapper) {
        return new PagePojo<>(
                page.getContent().stream().map(mapper).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumber(),
                page.getSize()
        );
    }
}
