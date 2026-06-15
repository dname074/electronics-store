package pl.javakurs.dname074.invoice_service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.javakurs.dname074.invoice.dto.InvoiceApiResponse;
import pl.javakurs.dname074.invoice.dto.InvoiceDto;
import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice_service.entity.InvoiceEntity;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalProviderId", source = "id")
    @Mapping(target = "invoiceNumber", source = "number")
    Invoice responseToPojo(InvoiceApiResponse response);
    InvoiceDto toDto(Invoice pojo);
    Invoice entityToPojo(InvoiceEntity entity);
    InvoiceEntity pojoToEntity(Invoice pojo);
}
