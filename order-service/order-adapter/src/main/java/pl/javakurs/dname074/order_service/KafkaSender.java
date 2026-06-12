package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.order.domain.KafkaSenderProvider;
import pl.javakurs.dname074.order.model.Order;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaSender implements KafkaSenderProvider {
    @Value("${order-service.created-orders-topic}")
    private String createdOrdersTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderMapper orderMapper;

    public void sendCreatedOrdersEvent(Order order) {
        log.info("Send event to topic: {}, with data: {}", createdOrdersTopic, order.toString());
        kafkaTemplate.send(createdOrdersTopic, orderMapper.toDto(order));
    }
}
