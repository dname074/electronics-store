package pl.javakurs.dname074.invoice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String town;
    private String postalCode;
    private String street;
    private String houseNumber;
}
