package pl.javakurs.dname074.cart_service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.dto.AddToCartCommand;
import pl.javakurs.dname074.cart.dto.CartDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartServiceProvider service;
    private final CartMapper mapper;

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable String id) {
        return mapper.toDto(service.getCart(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartDto addToCart(@RequestBody @Valid AddToCartCommand product) {
        return mapper.toDto(service.addToCart(product.cartId(), product.productId(), product.configurations()));
    }
}
