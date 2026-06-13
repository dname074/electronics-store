package pl.javakurs.dname074.order.dto;

public record CreateCustomerCommand(
        String firstName,
        String lastName,
        String country,
        String town,
        String postalCode,
        String street,
        Integer houseNumber
) {
}
