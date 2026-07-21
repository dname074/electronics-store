package pl.javakurs.dname074.electronics_store_bff;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javakurs.dname074.bff.dto.PageDto;
import pl.javakurs.dname074.bff.dto.ProductDto;

import java.math.BigDecimal;

@FeignClient(
        name = "productClient",
        configuration = GlobalClientConfiguration.class
)
interface ProductClient {
    @GetMapping("/products")
    PageDto<ProductDto> getProductsPage(@RequestParam Integer page,
                                        @RequestParam Integer size,
                                        @RequestParam String type,
                                        @RequestParam BigDecimal minPrice,
                                        @RequestParam BigDecimal maxPrice);
    @GetMapping("/products/{id}")
    ProductDto getProduct(@PathVariable Long id);
}
