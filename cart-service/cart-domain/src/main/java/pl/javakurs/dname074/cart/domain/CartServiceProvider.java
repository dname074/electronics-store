package pl.javakurs.dname074.cart.domain;

import pl.javakurs.dname074.cart.model.CartProduct;

import java.util.List;
import java.util.UUID;

public interface CartServiceProvider {
    CartProduct addToCart(UUID cartId, Long productId, List<Long> configurationIds);
}
