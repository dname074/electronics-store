package pl.javakurs.dname074.product_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.dto.CreateProductCommand;
import pl.javakurs.dname074.dto.ExceptionResponseDto;
import pl.javakurs.dname074.dto.FilteredProductsRequest;
import pl.javakurs.dname074.dto.PageDto;
import pl.javakurs.dname074.dto.ProductDto;
import pl.javakurs.dname074.dto.ValidExceptionResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
class ProductController {
    private final ProductServiceFacade productService;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;

    @Operation(summary = "Get products page based on params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageDto.class))
                    }),
            @ApiResponse(responseCode = "204", description = "Products not found",
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
    public PageDto<ProductDto> getProductsPage(@Valid FilteredProductsRequest request) {
        log.info("Received GET /products request with params: page = {}, size = {}, type = {}, minPrice = {}, maxPrice = {}",
                request.getPage(), request.getSize(), request.getType(), request.getMinPrice(), request.getMaxPrice());
        return pageMapper.toDto(
                productService.getProductsPage(request.getPage(), request.getSize(), request.getType(), request.getMinPrice(), request.getMaxPrice()),
                productMapper::pojoToDto
        );
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
        log.info("Received GET /products/{} request", id);
        return productMapper.pojoToDto(productService.getProduct(id));
    }

    @Operation(summary = "Add product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Product already exists",
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto addProduct(@RequestBody @Valid CreateProductCommand product) {
        log.info("Received POST /products request with body: {}", product.toString());
        return productMapper.pojoToDto(productService.addProduct(productMapper.dtoToPojo(product)));
    }

    @Operation(summary = "Modify existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product modified",
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
    @PutMapping("/{id}")
    public ProductDto modifyProduct(@PathVariable Long id, @RequestBody @Valid CreateProductCommand product) {
        log.info("Received PUT /products/{} request with body: {}", id, product.toString());
        return productMapper.pojoToDto(productService.modifyProduct(id, productMapper.dtoToPojo(product)));
    }

    @Operation(summary = "Add configuration to product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Product not found or configuration not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Product already contains default configuration",
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
    @PatchMapping("/{productId}/configurations/{configurationId}")
    public ProductDto addConfigurationToProduct(@PathVariable Long productId, @PathVariable Long configurationId,
                                                @RequestParam(required = false, defaultValue = "false") Boolean isDefault) {
        log.info("Received PATCH /products/{}}/configurations/{} request with param isDefault = {}",
                productId, configurationId, isDefault);
        return productMapper.pojoToDto(productService.addConfigurationToProduct(productId, configurationId, isDefault));
    }

    @Operation(summary = "Remove product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found and removed",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
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
    @DeleteMapping("/{id}")
    public ProductDto removeProduct(@PathVariable Long id) {
        log.info("Received DELETE /products/{} request", id);
        return productMapper.pojoToDto(productService.removeProduct(id));
    }
}
