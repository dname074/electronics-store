package pl.javakurs.dname074.cart_service;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.domain.CartRepositoryProvider;
import pl.javakurs.dname074.cart.model.Cart;

import java.util.Optional;

@RequiredArgsConstructor
class CartRepositoryAdapter implements CartRepositoryProvider {
    private final CartRepository repository;

    @Override
    public void save(Cart cart) {
        repository.save(cart);
    }

    @Override
    public Optional<Cart> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void delete(String cartId) {
        repository.deleteById(cartId);
    }
}
