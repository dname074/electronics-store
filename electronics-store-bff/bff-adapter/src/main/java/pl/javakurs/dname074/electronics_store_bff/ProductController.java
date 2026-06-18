package pl.javakurs.dname074.electronics_store_bff;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.bff.domain.ProductServiceProvider;
import pl.javakurs.dname074.bff.dto.ExceptionResponseDto;
import pl.javakurs.dname074.bff.dto.PageDto;
import pl.javakurs.dname074.bff.dto.ProductDto;
import pl.javakurs.dname074.bff.dto.ProductsRequest;
import pl.javakurs.dname074.bff.dto.ValidExceptionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
class ProductController {
    private final ProductServiceProvider service;
    private final PageMapper pageMapper;
    private final ProductMapper productMapper;

    @Operation(summary = "Get products page based on params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    })
    })
    @GetMapping
    public PageDto<ProductDto> getProductsPage(@Valid ProductsRequest request) {
        log.info("Received GET /api/v1/products request with params: page = {}, size = {}, type = {}, minPrice = {}, maxPrice = {}",
                request.getPage(), request.getSize(), request.getType(), request.getMinPrice(), request.getMaxPrice());
        return pageMapper.toDto(service.getProductsPage(
                request.getPage(), request.getSize(), request.getType(), request.getMinPrice(), request.getMaxPrice()
        ), productMapper::toDto);
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    })
    })
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        log.info("Received GET /api/v1/products/{} request", id);
        return productMapper.toDto(service.getProduct(id));
    }
}
