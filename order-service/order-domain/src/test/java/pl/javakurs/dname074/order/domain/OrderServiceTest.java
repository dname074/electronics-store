package pl.javakurs.dname074.order.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javakurs.dname074.order.model.Customer;
import pl.javakurs.dname074.order.model.exception.ExternalClientException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    OrderRepositoryProvider repository;
    @Mock
    CartClientProvider client;
    @InjectMocks
    OrderService service;

    @Test
    void createOrder_CartNotFound_ExternalClientException() {
        String cartId = "8d379dc8-af0f-4122-85d5-39064cf092b7";
        Customer customer = new Customer(null, "Jan", "Kowalski", "Polska", "Warszawa", "50-660", "Szybka", 8, null);
        when(client.getCart(cartId)).thenThrow(ExternalClientException.class);

        assertThrows(ExternalClientException.class, () -> service.createOrder(cartId, customer));

        verifyNoInteractions(repository);
        verify(client, times(1)).getCart(anyString());
        verifyNoMoreInteractions(client);
    }
}
