package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.model.CartProduct;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CartService implements CartServiceProvider {
    @Override
    public CartProduct addToCart(UUID cartId, Long productId, List<Long> configurationIds) {
        return null;
    }
}
