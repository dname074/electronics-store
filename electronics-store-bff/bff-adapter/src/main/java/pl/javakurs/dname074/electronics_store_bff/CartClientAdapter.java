package pl.javakurs.dname074.electronics_store_bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.bff.domain.CartClientProvider;
import pl.javakurs.dname074.bff.dto.AddToCartCommand;
import pl.javakurs.dname074.bff.model.Cart;

import java.util.List;

@Component
@RequiredArgsConstructor
class CartClientAdapter implements CartClientProvider {
    private final CartClient client;
    private final CartMapper mapper;

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurations) {
        return mapper.toPojo(client.addToCart(new AddToCartCommand(cartId, productId, configurations)));
    }

    @Override
    public Cart getCart(String id) {
        return mapper.toPojo(client.getCart(id));
    }
}
