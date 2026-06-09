package pl.javakurs.dname074.order_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.javakurs.dname074.order.dto.OrderCartDto;

@FeignClient(
        name = "cartServiceClient",
        configuration = CartClientConfiguration.class
)
public interface CartClient {
    @GetMapping("/carts/{id}")
    OrderCartDto getCart(@PathVariable("id") String id);
    @DeleteMapping("/carts/{id}")
    OrderCartDto removeCart(@PathVariable("id") String id);
}
