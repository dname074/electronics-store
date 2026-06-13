package pl.javakurs.dname074.order.model;

import lombok.*;

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", order_id=" + order.getId() +
                '}';
    }
}
