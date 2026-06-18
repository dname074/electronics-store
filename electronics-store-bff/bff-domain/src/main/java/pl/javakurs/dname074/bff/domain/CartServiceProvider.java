package pl.javakurs.dname074.bff.domain;

import pl.javakurs.dname074.bff.model.Cart;

import java.util.List;

public interface CartServiceProvider {
    Cart addToCart(String cartId, Long productId, List<Long> configurations);

    Cart getCart(String id);
}
