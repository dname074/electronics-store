package pl.javakurs.dname074.invoice_service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.javakurs.dname074.invoice.dto.InvoiceProductDto;
import pl.javakurs.dname074.invoice.model.InvoiceProduct;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "totalPriceGross", source = "totalPrice")
    InvoiceProduct toPojo(InvoiceProductDto dto);
}
