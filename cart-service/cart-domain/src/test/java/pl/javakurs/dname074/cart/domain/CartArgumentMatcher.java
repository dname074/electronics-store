package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import org.mockito.ArgumentMatcher;
import pl.javakurs.dname074.cart.model.Cart;

import java.util.Objects;

@RequiredArgsConstructor
public class CartArgumentMatcher implements ArgumentMatcher<Cart> {
    private final Cart cart;

    @Override
    public boolean matches(Cart cart) {
        return Objects.nonNull(cart) &&
                this.cart.getId().equals(cart.getId()) &&
                this.cart.getTotalPrice().equals(cart.getTotalPrice()) &&
                this.cart.getProducts().size() == cart.getProducts().size();
    }
}
