package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CartService implements CartServiceProvider {
    private final CartRepositoryProvider repository;
    private final ProductClientProvider productClient;

    // todo: feign client dla product-service'u + mappery

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurationIds) {
        Cart cart = (cartId != null)
                ? repository.findById(cartId).orElse(createNewCart())
                : createNewCart();
        CartProduct product = productClient.getProduct(productId);

        cart.addProduct(product);
        repository.save(cart);
        return cart;
    }

    private Cart createNewCart() {
        return new Cart(UUID.randomUUID().toString(), new ArrayList<>(), BigDecimal.ZERO);
    }
}
