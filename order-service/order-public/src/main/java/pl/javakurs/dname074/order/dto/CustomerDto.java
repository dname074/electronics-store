package pl.javakurs.dname074.order.dto;

public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String country,
        String town,
        String postalCode,
        String street,
        String houseNumber) {
}