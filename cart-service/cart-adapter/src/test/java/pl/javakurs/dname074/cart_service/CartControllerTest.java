package pl.javakurs.dname074.cart_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.ConfigType;
import pl.javakurs.dname074.cart.model.Configuration;
import pl.javakurs.dname074.cart.model.ProductType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ContainerConfig.class)
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CartMapper mapper;
    @MockitoBean
    CartServiceProvider cartService;

    @Test
    void getCart_DataCorrect_CartDtoReturned() throws Exception {
        String cartId = "9a8f4c22-9154-47b2-841f-13a85b9b2da3";
        CartProduct product = new CartProduct(1L, "fdsg-4354-g4532",
                "Komputer", BigDecimal.valueOf(5599), ProductType.COMPUTER,
                "Komputer", List.of(new Configuration(1L, "ram_16_gb_ddr5", ConfigType.RAM,
                BigDecimal.valueOf(999.99), "RAM 16 GB", true)));
        List<CartProduct> products = new ArrayList<>(List.of(product));
        Cart cart = new Cart(
                cartId,
                products,
                BigDecimal.valueOf(5599));

        when(cartService.getCart(cartId)).thenReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders.get("/carts/{cartId}", cartId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId))
                .andExpect(jsonPath("$.products").isNotEmpty())
                .andExpect(jsonPath("$.total_price").value("5599"));

        verify(cartService, times(1)).getCart(cartId);
        verifyNoMoreInteractions(cartService);
    }
}
