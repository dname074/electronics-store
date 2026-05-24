package pl.javakurs.dname074.cart.dto;

import java.util.List;
import java.util.UUID;

public record AddToCartCommand(
        UUID cartId,
        Long productId,
        List<Long> configurations
) {
}
