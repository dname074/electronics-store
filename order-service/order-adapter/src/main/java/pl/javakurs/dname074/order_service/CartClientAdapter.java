package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.order.domain.CartClientProvider;
import pl.javakurs.dname074.order.model.OrderCart;

@Component
@RequiredArgsConstructor
class CartClientAdapter implements CartClientProvider {
    private final CartClient client;
    private final CartMapper cartMapper;

    @Override
    public OrderCart getCart(String cartId) {
        return cartMapper.toPojo(client.getCart(cartId));
    }

    @Override
    public OrderCart removeCart(String cartId) {
        return cartMapper.toPojo(client.removeCart(cartId));
    }
}
