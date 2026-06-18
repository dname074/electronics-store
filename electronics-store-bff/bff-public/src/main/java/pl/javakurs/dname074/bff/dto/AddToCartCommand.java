package pl.javakurs.dname074.bff.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddToCartCommand(
        @Size(min = 36, max = 36)
        String cartId,
        @NotNull
        Long productId,
        List<Long> configurations
) {
}
