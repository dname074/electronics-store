package pl.javakurs.dname074.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOrderCommand(
        @NotBlank
        @Size(min = 36, max = 36)
        String cartId,
        @NotNull
        CreateCustomerCommand customer
) {
}
