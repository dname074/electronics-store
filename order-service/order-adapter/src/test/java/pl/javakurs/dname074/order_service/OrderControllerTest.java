package pl.javakurs.dname074.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.javakurs.dname074.order.dto.CreateOrderCommand;
import pl.javakurs.dname074.order.model.ConfigType;
import pl.javakurs.dname074.order.model.Configuration;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.OrderProduct;
import pl.javakurs.dname074.order.model.OrderStatus;
import pl.javakurs.dname074.order.model.ProductType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ContainerConfig.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PageMapper pageMapper;
    @Autowired
    OrderMapper orderMapper;
    @MockitoBean
    OrderServiceFacade orderService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createOrder_DataCorrect_201Returned() throws Exception {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand("8d379dc8-af0f-4122-85d5-39064cf092bs");
        List<Configuration> configurationSnapshot = List.of(new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16GB", true));
        List<OrderProduct> products = List.of(new OrderProduct(1L, "ES-1234-2314", "Computer", BigDecimal.valueOf(4000),
                ProductType.COMPUTER, "Computer super", configurationSnapshot));
        Order order = new Order(1L, OrderStatus.CREATED, BigDecimal.valueOf(5000), products, Instant.ofEpochMilli(1000000), Instant.ofEpochMilli(1000000));

        when(orderService.createOrder(createOrderCommand.cartId())).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(createOrderCommand))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value(OrderStatus.CREATED.toString()))
                .andExpect(jsonPath("$.total_price").value(BigDecimal.valueOf(5000).toString()))
                .andExpect(jsonPath("$.products").isNotEmpty())
                .andExpect(jsonPath("$.created_at").value(Instant.ofEpochMilli(1000000).toString()));
    }

    @Test
    void getOrdersHistory_InvalidParametersPassed_400Returned() throws Exception {
        int page = 0;
        int size = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .param("wrong_parameter", String.valueOf(page))
                .param("wrong_parameter_2", String.valueOf(size))
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
