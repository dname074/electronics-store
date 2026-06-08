package pl.javakurs.dname074.order.domain;

import pl.javakurs.dname074.order.model.OrderCart;

public interface CartClientProvider {
    OrderCart getCart(String cartId);
}
