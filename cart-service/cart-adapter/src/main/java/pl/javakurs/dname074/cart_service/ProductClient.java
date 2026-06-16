package pl.javakurs.dname074.cart_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.javakurs.dname074.cart.dto.CartProductDto;

@FeignClient(
        name = "productClient",
        configuration = ProductClientConfiguration.class
)
interface ProductClient {
    @GetMapping("/products/{id}")
    CartProductDto getProduct(@PathVariable Long id);
}
