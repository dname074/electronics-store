package pl.javakurs.dname074.bff.dto;

public record CustomerDto(
        String firstName,
        String lastName,
        String country,
        String town,
        String postalCode,
        String street,
        String houseNumber) {
}