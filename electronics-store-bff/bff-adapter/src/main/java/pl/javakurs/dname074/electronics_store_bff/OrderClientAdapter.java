package pl.javakurs.dname074.electronics_store_bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.bff.domain.OrderClientProvider;
import pl.javakurs.dname074.bff.dto.CreateOrderCommand;
import pl.javakurs.dname074.bff.model.Customer;
import pl.javakurs.dname074.bff.model.Order;

@Component
@RequiredArgsConstructor
class OrderClientAdapter implements OrderClientProvider {
    private final OrderClient client;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Override
    public Order createOrder(String cartId, Customer customer) {
        return orderMapper.toPojo(client.createOrder(
                new CreateOrderCommand(cartId, customerMapper.pojoToCommand(customer))
        ));
    }
}
