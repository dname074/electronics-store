package pl.javakurs.dname074.electronics_store_bff;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.javakurs.dname074.bff.dto.CreateOrderCommand;
import pl.javakurs.dname074.bff.dto.OrderDto;

@FeignClient(
        name = "orderClient",
        configuration = GlobalClientConfiguration.class
)
interface OrderClient {
    @PostMapping("/orders")
    OrderDto createOrder(@RequestBody @Valid CreateOrderCommand order);
}
