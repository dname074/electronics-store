package pl.javakurs.dname074.invoice_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.invoice.dto.CustomerDto;
import pl.javakurs.dname074.invoice.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toPojo(CustomerDto dto);
}
