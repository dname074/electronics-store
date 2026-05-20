package pl.javakurs.dname074.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javakurs.dname074.model.ConfigType;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.model.InvalidProductTypeException;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.model.exception.ResourceAlreadyExistsException;
import pl.javakurs.dname074.model.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConfigurationServiceTest {
    @Mock
    private ConfigurationRepositoryProvider configurationRepository;
    @Mock
    private ProductRepositoryProvider productRepository;
    @InjectMocks
    private ConfigurationService service;

    @Test
    void addConfiguration_DataCorrect_ConfigurationReturned() {
        Configuration configuration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", null);

        when(configurationRepository.existsByNameAndType(configuration.getName(), configuration.getType())).thenReturn(false);
        when(configurationRepository.save(any())).thenReturn(configuration);

        Configuration result = service.addConfiguration(configuration, null);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ram_16", result.getName()),
                () -> assertEquals(ConfigType.RAM, result.getType()),
                () -> assertEquals(BigDecimal.valueOf(999.99), result.getPrice()),
                () -> assertEquals("Pamięć RAM 16 GB", result.getLabel()),
                () -> assertNull(result.getProduct())
        );

        verify(configurationRepository, times(1)).existsByNameAndType("ram_16", ConfigType.RAM);
        verify(configurationRepository, times(1)).save(argThat(new ConfigurationArgumentMatcher(configuration)));
        verifyNoMoreInteractions(configurationRepository);
        verifyNoInteractions(productRepository);
    }

    @Test
    void addConfiguration_ConfigurationExists_ResourceAlreadyExistsExceptionThrown() {
        Configuration configuration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", null);

        when(configurationRepository.existsByNameAndType(configuration.getName(), configuration.getType())).thenReturn(true);

        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class, () -> service.addConfiguration(configuration, null));

        assertEquals("Provided configuration already exists", exception.getMessage());

        verify(configurationRepository, times(1)).existsByNameAndType("ram_16", ConfigType.RAM);
        verifyNoMoreInteractions(configurationRepository);
        verifyNoInteractions(productRepository);
    }

    @Test
    void addConfiguration_DataCorrectWithProductId_ConfigurationReturned() {
        long productId = 1L;
        Product product = new Product(productId, "ES-5435-4536", "ram_16",
                BigDecimal.valueOf(999.99), ProductType.RAM, "Pamięć RAM 16 GB", List.of());
        Configuration configuration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", null);
        Configuration modifiedConfiguration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", product);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(configurationRepository.save(configuration)).thenReturn(configuration);

        Configuration result = service.addConfiguration(configuration, productId);

        Assertions.assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("ram_16", result.getName()),
                () -> assertEquals(ConfigType.RAM, result.getType()),
                () -> assertEquals(BigDecimal.valueOf(999.99), result.getPrice()),
                () -> assertEquals("Pamięć RAM 16 GB", result.getLabel()),
                () -> assertEquals("ES-5435-4536", result.getProduct().getSku())
        );

        verify(productRepository, times(1)).findById(1L);
        verify(configurationRepository, times(1)).save(argThat(new ConfigurationArgumentMatcher(modifiedConfiguration)));
        verifyNoMoreInteractions(configurationRepository);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void addConfiguration_ProductWithProvidedIdDoesntExist_ResourceNotFoundExceptionThrown() {
        long productId = 1L;
        Configuration configuration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", null);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.addConfiguration(configuration, productId));

        assertEquals("Product with provided id not found", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }

    @Test
    void addConfiguration_InvalidConfigType_InvalidProductTypeExceptionThrown() {
        long productId = 1L;
        Product product = new Product(productId, "ES-1234-5678", "cpu_i5_11500",
                BigDecimal.valueOf(1499.99), ProductType.CPU, "Procesor Intel Core i5 11500", List.of());
        Configuration configuration = new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "Pamięć RAM 16 GB", null);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        InvalidProductTypeException exception = assertThrows(InvalidProductTypeException.class, () -> service.addConfiguration(configuration, productId));

        assertEquals("Product type doesn't match provided configuration's type", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(configurationRepository);
    }
}
