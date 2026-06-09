package pl.javakurs.dname074.cart.domain;

import pl.javakurs.dname074.cart.model.Cart;

import java.util.Optional;

public interface CartRepositoryProvider {
    void save(Cart cart);
    Optional<Cart> findById(String id);
    void delete(String cartId);
}
