package pl.javakurs.dname074.cart.domain;

import pl.javakurs.dname074.cart.model.Cart;

import java.util.List;

public interface CartServiceProvider {
    Cart addToCart(String cartId, Long productId, List<Long> configurationIds);
}
