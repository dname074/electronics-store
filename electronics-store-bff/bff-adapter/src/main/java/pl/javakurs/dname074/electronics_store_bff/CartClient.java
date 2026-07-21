package pl.javakurs.dname074.electronics_store_bff;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javakurs.dname074.bff.dto.AddToCartCommand;
import pl.javakurs.dname074.bff.dto.CartDto;

@FeignClient(
        name = "cartClient",
        configuration = GlobalClientConfiguration.class
)
interface CartClient {
    @PostMapping("/carts")
    CartDto addToCart(AddToCartCommand product);
    @GetMapping("/carts/{id}")
    CartDto getCart(@PathVariable String id);
}
