package pl.javakurs.dname074.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String id;
    private List<CartProduct> products;
    private BigDecimal totalPrice;

    public void addProduct(CartProduct product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void calculateTotalPrice() {
        if (this.products == null || this.totalPrice == null) {
            return;
        }
        this.totalPrice = products.stream()
                .map(CartProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
