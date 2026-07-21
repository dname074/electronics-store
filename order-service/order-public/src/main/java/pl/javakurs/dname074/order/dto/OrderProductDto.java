package pl.javakurs.dname074.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.javakurs.dname074.order.model.ProductType;

import java.math.BigDecimal;
import java.util.List;

public record OrderProductDto(
        Long id,
        String sku,
        String name,
        BigDecimal totalPrice,
        ProductType type,
        String label,
        @JsonProperty("configurations")
        List<ConfigurationDto> configurationSnapshot
) {
}
