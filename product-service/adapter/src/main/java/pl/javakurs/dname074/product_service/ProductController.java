package pl.javakurs.dname074.product_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductServiceProvider productService;
    // add, delete, update, products page, available configurations

    @GetMapping
    public Page<Product> getProductsPage(Pageable pageable) {
        return productService.getProductsPage(pageable);
    }
}
