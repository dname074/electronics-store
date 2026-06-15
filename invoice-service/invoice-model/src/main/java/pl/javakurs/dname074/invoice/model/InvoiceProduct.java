package pl.javakurs.dname074.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceProduct {
    private String name;
    private Integer tax;
    private BigDecimal totalPriceGross;
    private Integer quantity;
}
