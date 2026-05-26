package pl.javakurs.dname074.cart.dto;

import java.util.List;

public record AddToCartCommand(
        String cartId,
        Long productId,
        List<Long> configurations
) {
}
