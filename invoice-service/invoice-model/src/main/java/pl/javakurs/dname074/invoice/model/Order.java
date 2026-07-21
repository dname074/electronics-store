package pl.javakurs.dname074.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
        private Long id;
        private BigDecimal totalPrice;
        private List<InvoiceProduct> products;
        private Customer customer;
        private Instant createdAt;
        private Instant updatedAt;
}
