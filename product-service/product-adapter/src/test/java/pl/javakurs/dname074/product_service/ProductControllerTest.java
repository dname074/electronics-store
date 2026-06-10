package pl.javakurs.dname074.product_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ContainerConfig.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProductServiceFacade productService;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    PageMapper pageMapper;

    @Test
    void getProductsPage_MinimumPriceBiggerThanMaximumPrice_ValidExceptionResponseDtoThrown() throws Exception {
        ProductType type = ProductType.COMPUTER;
        BigDecimal minPrice = BigDecimal.valueOf(4999);
        BigDecimal maxPrice = BigDecimal.valueOf(2999);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("type", type.toString())
                        .param("minPrice", minPrice.toString())
                        .param("maxPrice", maxPrice.toString()))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
