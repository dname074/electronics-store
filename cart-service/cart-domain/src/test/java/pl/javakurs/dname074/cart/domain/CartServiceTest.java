package pl.javakurs.dname074.cart.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.ConfigType;
import pl.javakurs.dname074.cart.model.Configuration;
import pl.javakurs.dname074.cart.model.ProductType;
import pl.javakurs.dname074.cart.model.exception.InvalidConfigurationException;
import pl.javakurs.dname074.cart.model.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CartServiceTest {
    CartRepositoryProvider repository;
    ProductClientProvider client;
    ConfigurationValidator configurationValidator;
    CartService service;

    @BeforeEach
    void init() {
        this.repository = Mockito.mock(CartRepositoryProvider.class);
        this.client = Mockito.mock(ProductClientProvider.class);
        this.configurationValidator = new ConfigurationValidator();
        this.service = new CartService(this.repository, this.client, this.configurationValidator);
    }

    @Test
    void getCart_IdCorrect_CartReturned() {
        String id = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        Cart cart = new Cart(
                id,
                List.of(),
                BigDecimal.ZERO);
        when(repository.findById(id)).thenReturn(Optional.of(cart));

        Cart result = service.getCart(id);

        Assertions.assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(cart.getProducts().size(), result.getProducts().size()),
                () -> assertEquals(cart.getTotalPrice(), result.getTotalPrice())
        );
        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(client);
    }

    @Test
    void getCart_CartNotFound_ResourceNotFoundExceptionThrown() {
        String id = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.getCart(id));

        assertEquals("Cart with provided id not found", exception.getMessage());
        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(client);
    }

    @Test
    void addToCart_DataCorrect_CartReturned() {
        String id = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        Long productId = 1L;
        List<Long> configurationIds = List.of(1L);
        CartProduct product = new CartProduct(productId, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16 GB", true)));
        CartProduct product2 = new CartProduct(2L, "ffdd-ge34-5rfd",
                "Komputer2", BigDecimal.valueOf(6000), BigDecimal.valueOf(7000), ProductType.COMPUTER,
                "Komputer2", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16 GB", true)));

        List<CartProduct> products = new ArrayList<>(List.of(product2));
        Cart cart = new Cart(
                id,
                products,
                BigDecimal.valueOf(7000));
        List<CartProduct> products2 = new ArrayList<>(List.of(product, product2));
        Cart expectedCart = new Cart(
                id,
                products2,
                BigDecimal.valueOf(13000));

        when(repository.findById(id)).thenReturn(Optional.of(cart));
        when(client.getProduct(productId)).thenReturn(product);
        doNothing().when(repository).save(any());

        Cart result = service.addToCart(id, productId, configurationIds);

        Assertions.assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(expectedCart.getProducts().size(), result.getProducts().size()),
                () -> assertEquals(expectedCart.getTotalPrice(), result.getTotalPrice())
        );
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(argThat(new CartArgumentMatcher(expectedCart)));
        verify(client, times(1)).getProduct(productId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(client);
    }

    @Test
    void addToCart_ProductNotFound_ResourceNotFoundExceptionThrown() {
        String cartId = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        Long productId = 1L;
        List<Long> configurationIds = List.of(1L);
        Cart cart = new Cart(
                cartId,
                List.of(),
                BigDecimal.valueOf(6199));

        when(repository.findById(cartId)).thenReturn(Optional.of(cart));
        when(client.getProduct(productId)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class,
                ()-> service.addToCart(cartId, productId, configurationIds));

        verify(repository, times(1)).findById(cartId);
        verify(client, times(1)).getProduct(productId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(client);
    }

    @Test
    void addToCart_InvalidConfigurationIdProvided_InvalidConfigurationExceptionThrown() {
        String cartId = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        Long productId = 1L;
        List<Long> configurationIds = List.of(2L);
        CartProduct product = new CartProduct(productId, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5000), BigDecimal.valueOf(5999.99), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "RAM 16 GB", true)));
        Cart cart = new Cart(
                cartId,
                new ArrayList<>(),
                BigDecimal.ZERO);

        when(repository.findById(cartId)).thenReturn(Optional.of(cart));
        when(client.getProduct(productId)).thenReturn(product);

        InvalidConfigurationException exception = assertThrows(InvalidConfigurationException.class,
                ()-> service.addToCart(cartId, productId, configurationIds));

        assertEquals("Unknown configuration IDs: " + configurationIds, exception.getMessage());

        verify(repository, times(1)).findById(cartId);
        verify(client, times(1)).getProduct(productId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(client);
    }

    @Test
    void addToCart_CartIdNotProvided_CartCreatedAndReturned() {
        Long productId = 1L;
        List<Long> configurationIds = List.of(1L);
        CartProduct product = new CartProduct(productId, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5000), BigDecimal.valueOf(5999.99), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "RAM 16 GB", true)));

        List<CartProduct> products = new ArrayList<>(List.of(product));
        Cart expectedCart = new Cart(
                null,
                products,
                BigDecimal.valueOf(5999.99));

        when(client.getProduct(productId)).thenReturn(product);
        doNothing().when(repository).save(any());

        Cart result = service.addToCart(null, productId, configurationIds);

        Assertions.assertAll(
                () -> assertNotNull(result.getId()),
                () -> assertEquals(expectedCart.getProducts().size(), result.getProducts().size()),
                () -> assertEquals(expectedCart.getTotalPrice(), result.getTotalPrice())
        );

        verify(repository, times(1)).save(any());
        verify(client, times(1)).getProduct(productId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(client);
    }

    @Test
    void addToCart_NoConfigurationsProvided_CartReturnedWithDefaultConfigurations() {
        String id = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        Long productId = 1L;
        List<Long> configurationIds = List.of();
        CartProduct product = new CartProduct(productId, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16 GB", true),
                new Configuration(2L, "ram_32_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 32 GB", false)));
        CartProduct expectedProduct = new CartProduct(productId, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16 GB", true)));

        List<CartProduct> products = new ArrayList<>(List.of(expectedProduct));
        Cart cart = new Cart(
                id,
                new ArrayList<>(),
                BigDecimal.ZERO);
        Cart expectedCart = new Cart(
                id,
                products,
                BigDecimal.valueOf(6000));

        when(repository.findById(id)).thenReturn(Optional.of(cart));
        when(client.getProduct(productId)).thenReturn(product);
        doNothing().when(repository).save(any());

        Cart result = service.addToCart(id, productId, configurationIds);

        Assertions.assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(expectedCart.getProducts().size(), result.getProducts().size()),
                () -> assertEquals(expectedCart.getTotalPrice(), result.getTotalPrice()),
                () -> assertEquals(expectedCart.getProducts().getFirst().getConfigurations().size(),
                        expectedCart.getProducts().getFirst().getConfigurations().size()),
                () -> assertTrue(result.getProducts().getFirst().getConfigurations().getFirst().getIsDefault())
        );
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(argThat(new CartArgumentMatcher(expectedCart)));
        verify(client, times(1)).getProduct(productId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(client);
    }
}
