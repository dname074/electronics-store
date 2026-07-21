package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.dto.OrderDto;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true")
class InvoiceListener {
    private final InvoiceServiceFacade invoiceService;
    private final OrderMapper orderMapper;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(
                    delay = 1000,
                    multiplier = 2.0,
                    maxDelay = 30000
            ),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            include = {
                    SocketTimeoutException.class,
                    ConnectException.class,
                    RetriableException.class
            }
    )
    @KafkaListener(topics = "created-orders")
    public void listenForCreatedOrders(OrderDto orderCreated) {
        log.info("Received event : {}, from created-orders topic", orderCreated.toString());
        invoiceService.generateInvoice(orderMapper.toPojo(orderCreated));
    }

    @DltHandler
    public void logDlt(
            ConsumerRecord<String, OrderDto> record,
            @Header(KafkaHeaders.DLT_ORIGINAL_TOPIC) String originalTopic,
            @Header(KafkaHeaders.DLT_ORIGINAL_OFFSET) String originalOffset,
            @Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String error) {
        log.error("Event has been sent to DLT! Topic = {}, offset = {}, error = {}",
                originalTopic, originalOffset, error);
    }
}
