package pl.javakurs.dname074.electronics_store_bff;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.bff.dto.CreateCustomerCommand;
import pl.javakurs.dname074.bff.dto.CustomerDto;
import pl.javakurs.dname074.bff.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer pojo);
    Customer toPojo(CustomerDto dto);
    Customer commandToPojo(CreateCustomerCommand command);
    CreateCustomerCommand pojoToCommand(Customer customer);
}