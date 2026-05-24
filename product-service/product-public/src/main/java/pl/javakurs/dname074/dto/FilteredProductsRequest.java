package pl.javakurs.dname074.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
public class FilteredProductsRequest {
    private Integer page = 0;
    private Integer size = 10;
    private ProductType type;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    @AssertTrue(message = "Minimum price can't be greater than maximum price")
    public boolean isPriceRangeValid() {
        if (minPrice != null && maxPrice != null) {
            return minPrice.compareTo(maxPrice) <= 0;
        }
        return true;
    }
}
