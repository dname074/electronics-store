package pl.javakurs.dname074.bff.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.bff.model.Cart;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CartService implements CartServiceProvider {
    private final CartClientProvider client;

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurations) {
        log.info("Process of sending request to add a product to cart has started");
        return client.addToCart(cartId, productId, configurations);
    }

    @Override
    public Cart getCart(String id) {
        log.info("Process of sending request to get a cart has started");
        return client.getCart(id);
    }
}
