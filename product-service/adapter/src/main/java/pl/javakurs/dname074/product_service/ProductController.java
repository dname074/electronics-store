package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.javakurs.dname074.domain.ProductServiceProvider;
import pl.javakurs.dname074.dto.CreateProductCommand;
import pl.javakurs.dname074.dto.PageDto;
import pl.javakurs.dname074.dto.ProductDto;
import pl.javakurs.dname074.model.Product;

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
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product addProduct(CreateProductCommand product) {
        return null;
    }

    @PutMapping("/{id}")
    public Product modifyProduct(@PathVariable Long id, CreateProductCommand product) {
        return null;
    }

    @PatchMapping("/{productId}/configurations/{configurationId}")
    public Product addConfigurationToProduct(@PathVariable Long productId, @PathVariable Long configurationId) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Product removeProduct(@PathVariable Long id) {
        return null;
    }
}
