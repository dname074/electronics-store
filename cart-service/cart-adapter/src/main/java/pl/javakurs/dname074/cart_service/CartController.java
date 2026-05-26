package pl.javakurs.dname074.cart_service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.dto.AddToCartCommand;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartServiceProvider service;
//    private final CartMapper mapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addToCart(@RequestBody @Valid AddToCartCommand product) {
        return service.addToCart(product.cartId(), product.productId(), product.configurations());
    }
}
