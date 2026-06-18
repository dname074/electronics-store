package pl.javakurs.dname074.bff.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
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
    private Integer houseNumber;
    private Order order;
}
