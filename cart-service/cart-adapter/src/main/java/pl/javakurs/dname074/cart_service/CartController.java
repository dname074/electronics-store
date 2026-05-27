package pl.javakurs.dname074.cart_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.dto.AddToCartCommand;
import pl.javakurs.dname074.cart.dto.CartDto;
import pl.javakurs.dname074.cart.dto.ExceptionResponseDto;
import pl.javakurs.dname074.cart.dto.ValidExceptionResponseDto;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartServiceProvider service;
    private final CartMapper mapper;

    @Operation(summary = "Get cart by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request, no parameter passed or wrong parameter passed",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    }),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
    })
    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable String id) {
        log.info("Received GET /api/v1/carts/{} request", id);
        return mapper.toDto(service.getCart(id));
    }

    @Operation(summary = "Add product to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to cart",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid configurations, wrong parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    }),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Duplicated configurations",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
    })
    @PostMapping
    public CartDto addToCart(@RequestBody @Valid AddToCartCommand product) {
        log.info("Received POST /api/v1/carts request with body: cartId = {}, productId = {}, configurations : {}",
                product.cartId(), product.productId(), product.configurations());
        return mapper.toDto(service.addToCart(product.cartId(), product.productId(), product.configurations()));
    }
}
