package pl.javakurs.dname074.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javakurs.dname074.model.ConfigType;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductConfiguration;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.model.exception.AlreadyContainsDefaultConfigException;
import pl.javakurs.dname074.model.exception.ResourceAlreadyExistsException;
import pl.javakurs.dname074.model.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepositoryProvider productRepository;
    @Mock
    private ConfigurationRepositoryProvider configurationRepository;
    @InjectMocks
    private ProductService service;

    @Test
    void getProductsPage_CorrectDataPassed_PageReturned() {
        int page = 0;
        int size = 1;
        ProductType type = ProductType.SMARTPHONE;
        List<Product> productsList = List.of(new Product(1L, "ES-1025-5543", "iphone_23",
                BigDecimal.valueOf(5499.99), ProductType.SMARTPHONE, "Iphone 23", List.of()));
        PagePojo<Product> productsPage = new PagePojo<>(productsList, 1, 1, page, size);

        when(productRepository.findAll(page, size, type, null, null)).thenReturn(productsPage);

        PagePojo<Product> result = service.getProductsPage(page, size, type, null, null);

        Assertions.assertAll(
                () -> assertEquals(productsList, result.getContent()),
                () -> assertEquals(1, result.getTotalPages()),
                () -> assertEquals(1, result.getTotalElements()),
                () -> assertEquals(0, result.getPageNumber()),
                () -> assertEquals(1, result.getPageSize())
        );
        verify(productRepository, times(1)).findAll(0, 1, ProductType.SMARTPHONE, null, null);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void getProduct_ProductFound_ProductReturned() {
        long id = 1L;
        Product product = new Product(id, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", List.of());

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = service.getProduct(id);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ES-1024-5443", result.getSku()),
                () -> assertEquals("computer_intel_12", result.getName()),
                () -> assertEquals(BigDecimal.valueOf(4499.99), result.getBasePrice()),
                () -> assertEquals(ProductType.COMPUTER, result.getType()),
                () -> assertEquals("Computer Giga Mocny", result.getLabel()),
                () -> assertEquals(List.of(), result.getConfigurations())
        );

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void getProduct_ProductNotFound_ResourceNotFoundExceptionThrown() {
        long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> service.getProduct(id)
        );

        assertEquals("Product with provided id does not exist.", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void addProduct_DataCorrect_ProductReturned() {
        Product product = new Product(1L, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", List.of());

        when(productRepository.findBySku("ES-1024-5443")).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(product);

        Product result = service.addProduct(product);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ES-1024-5443", result.getSku()),
                () -> assertEquals("computer_intel_12", result.getName()),
                () -> assertEquals(BigDecimal.valueOf(4499.99), result.getBasePrice()),
                () -> assertEquals(ProductType.COMPUTER, result.getType()),
                () -> assertEquals("Computer Giga Mocny", result.getLabel()),
                () -> assertEquals(List.of(), result.getConfigurations())
        );

        verify(productRepository, times(1)).findBySku("ES-1024-5443");
        verify(productRepository, times(1)).save(argThat(new ProductArgumentMatcher(product)));
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void addProduct_ProductAlreadyExists_ResourceAlreadyExistsExceptionThrown() {
        Product product = new Product(1L, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", List.of());

        when(productRepository.findBySku("ES-1024-5443")).thenReturn(Optional.of(product));

        ResourceAlreadyExistsException exception = assertThrows(
                ResourceAlreadyExistsException.class, () -> service.addProduct(product)
        );

        assertEquals("Product with provided sku, had been already added.", exception.getMessage());

        verify(productRepository, times(1)).findBySku("ES-1024-5443");
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void modifyProduct_DataCorrect_ProductReturned() {
        long id = 1L;
        Product product = new Product(id, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", List.of());
        Product modifiedProduct = new Product(id, "ES-1024-5443", "computer_intel_15", BigDecimal.valueOf(4999.99),
                ProductType.COMPUTER, "Computer Giga Mocny Bardzo", List.of());

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        Product result = service.modifyProduct(id, modifiedProduct);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ES-1024-5443", result.getSku()),
                () -> assertEquals("computer_intel_15", result.getName()),
                () -> assertEquals(BigDecimal.valueOf(4999.99), result.getBasePrice()),
                () -> assertEquals(ProductType.COMPUTER, result.getType()),
                () -> assertEquals("Computer Giga Mocny Bardzo", result.getLabel()),
                () -> assertEquals(List.of(), result.getConfigurations())
        );

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(argThat(new ProductArgumentMatcher(modifiedProduct)));
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void modifyProduct_ProductNotFound_ResourceNotFoundExceptionThrown() {
        long id = 1L;
        Product modifiedProduct = new Product(id, "ES-1024-5443", "computer_intel_15", BigDecimal.valueOf(4999.99),
                ProductType.COMPUTER, "Computer Giga Mocny Bardzo", List.of());

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> service.modifyProduct(id, modifiedProduct)
        );

        assertEquals("Product with provided id does not exist.", exception.getMessage());

        verify(productRepository, times(1)).findById(id);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void addConfigurationToProduct_DataCorrect_ProductReturned() {
        long productId = 1L;
        long configurationId = 1L;
        boolean isDefault = true;
        Product product = new Product(productId, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", new ArrayList<>());
        Configuration configuration = new Configuration(configurationId, "ram_16_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16GB DDR5", null);
        ProductConfiguration productConfiguration = new ProductConfiguration(configuration, isDefault);
        Product modifiedProduct = new Product(productId, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", new ArrayList<>(List.of(productConfiguration)));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(configurationRepository.findById(configurationId)).thenReturn(Optional.of(configuration));
        when(productRepository.save(product)).thenReturn(product);

        Product result = service.addConfigurationToProduct(productId, configurationId, isDefault);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ES-1024-5443", result.getSku()),
                () -> assertEquals("computer_intel_12", result.getName()),
                () -> assertEquals(BigDecimal.valueOf(4499.99), result.getBasePrice()),
                () -> assertEquals(ProductType.COMPUTER, result.getType()),
                () -> assertEquals("Computer Giga Mocny", result.getLabel()),
                () -> assertFalse(result.getConfigurations().isEmpty())
        );

        verify(productRepository, times(1)).findById(1L);
        verify(configurationRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(argThat(new ProductArgumentMatcher(modifiedProduct)));
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(configurationRepository);
    }

    @Test
    void addConfigurationToProduct_MultipleDefaultConfigurations_AlreadyContainsDefaultConfigException() {
        long productId = 1L;
        long configurationId = 1L;
        boolean isDefault = true;
        Configuration configuration = new Configuration(configurationId, "ram_16_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16GB DDR5", null);
        Product product = new Product(productId, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", new ArrayList<>(List.of(new ProductConfiguration(configuration, true))));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(configurationRepository.findById(configurationId)).thenReturn(Optional.of(configuration));

        AlreadyContainsDefaultConfigException exception = assertThrows(
                AlreadyContainsDefaultConfigException.class,
                () -> service.addConfigurationToProduct(productId, configurationId, isDefault)
        );

        assertEquals("Selected product already contains default configuration of provided config type.", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verify(configurationRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(configurationRepository);
    }

    @Test
    void removeProduct_ProductFound_ProductReturned() {
        long id = 1L;
        Product product = new Product(id, "ES-1024-5443", "computer_intel_12", BigDecimal.valueOf(4499.99),
                ProductType.COMPUTER, "Computer Giga Mocny", List.of());

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.delete(any())).thenReturn(product);

        Product result = service.removeProduct(id);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ES-1024-5443", result.getSku()),
                () -> assertEquals("computer_intel_12", result.getName()),
                () -> assertEquals(BigDecimal.valueOf(4499.99), result.getBasePrice()),
                () -> assertEquals(ProductType.COMPUTER, result.getType()),
                () -> assertEquals("Computer Giga Mocny", result.getLabel()),
                () -> assertEquals(List.of(), result.getConfigurations())
        );

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(argThat(new ProductArgumentMatcher(product)));
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void removeProduct_ProductNotFound_ProductReturned() {
        long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.removeProduct(id));

        assertEquals("Product with provided id does not exist.", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }
}
