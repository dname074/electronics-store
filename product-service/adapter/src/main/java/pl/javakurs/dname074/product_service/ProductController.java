package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.domain.ProductServiceProvider;
import pl.javakurs.dname074.dto.CreateProductCommand;
import pl.javakurs.dname074.dto.PageDto;
import pl.javakurs.dname074.dto.ProductDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceProvider productService;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;
    // add, delete, update, products page, available configurations

    @GetMapping
    public PageDto<ProductDto> getProductsPage(@RequestParam Integer page, @RequestParam Integer size) {
        PageDto<ProductDto> productPage = pageMapper.toDto(
                productService.getProductsPage(page, size),
                productMapper::pojoToDto
        );
        return productPage;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productMapper.pojoToDto(productService.getProduct(id));
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody CreateProductCommand product) {
        return productMapper.pojoToDto(productService.addProduct(productMapper.dtoToPojo(product)));
    }

    @PutMapping("/{id}")
    public ProductDto modifyProduct(@PathVariable Long id, @RequestBody CreateProductCommand product) {
        return productMapper.pojoToDto(productService.modifyProduct(id, productMapper.dtoToPojo(product)));
    }

    @PatchMapping("/{productId}/configurations/{configurationId}")
    public ProductDto addConfigurationToProduct(@PathVariable Long productId, @PathVariable Long configurationId, @RequestParam Boolean isDefault) {
        return productMapper.pojoToDto(productService.addConfigurationToProduct(productId, configurationId, isDefault));
    }

    @DeleteMapping("/{id}")
    public ProductDto removeProduct(@PathVariable Long id) {
        return null;
    }
}
