package pl.javakurs.dname074.electronics_store_bff;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.bff.domain.CartServiceProvider;
import pl.javakurs.dname074.bff.dto.AddToCartCommand;
import pl.javakurs.dname074.bff.dto.CartDto;
import pl.javakurs.dname074.bff.dto.ExceptionResponseDto;
import pl.javakurs.dname074.bff.dto.ValidExceptionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@Slf4j
class CartController {
    private final CartServiceProvider service;
    private final CartMapper mapper;

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
        log.info("Received POST /api/v1/carts request with body: {}", product.toString());
        return mapper.toDto(service.addToCart(product.cartId(), product.productId(), product.configurations()));
    }

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
    public CartDto getCart(@PathVariable @Size(min = 36, max = 36) String id) {
        log.info("Received GET /api/v1/carts/{} request", id);
        return mapper.toDto(service.getCart(id));
    }
}
