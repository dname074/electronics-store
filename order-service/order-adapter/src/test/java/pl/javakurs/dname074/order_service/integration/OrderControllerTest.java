package pl.javakurs.dname074.order_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.javakurs.dname074.order.dto.*;
import pl.javakurs.dname074.order.model.*;
import pl.javakurs.dname074.order_service.ConfigurationMapper;
import pl.javakurs.dname074.order_service.ContainerConfig;
import pl.javakurs.dname074.order_service.OrderRepository;
import pl.javakurs.dname074.order_service.entity.OrderCustomerEntity;
import pl.javakurs.dname074.order_service.entity.OrderEntity;
import pl.javakurs.dname074.order_service.entity.OrderProductEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWireMock(port = 8015)
@AutoConfigureMockMvc
@Import(ContainerConfig.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    WireMockServer cartClientMock;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrderRepository repository;

    ConfigurationMapper configurationMapper;

    @BeforeEach
    void setup() {
        this.configurationMapper = Mappers.getMapper(ConfigurationMapper.class);
        repository.deleteAll();
        cartClientMock.resetAll();

        OrderEntity order = new OrderEntity(null, OrderStatus.CREATED, BigDecimal.valueOf(5000),
                List.of(new OrderProductEntity(null, "ES-1234-2314", "Computer", BigDecimal.valueOf(4000),
                        ProductType.COMPUTER, "Computer super", createConfigurationList())),
                new OrderCustomerEntity(1L, "Jan", "Kowalski", "Polska", "Warszawa", "50-660", "Szybka", 8, null),
                Instant.ofEpochMilli(1000000), Instant.ofEpochMilli(1000000));
        order.getCustomer().setOrder(order);
        repository.save(order);
        repository.flush();
    }

    @Test
    void createOrder_CorrectDataPassed_OrderDtoReturned() throws Exception {
        CreateCustomerCommand customer = new CreateCustomerCommand("Jan", "Kowalski", "Polska", "Warszawa", "50-660", "Szybka", 8);
        CreateOrderCommand createOrderCommand = new CreateOrderCommand("8d379dc8-af0f-4122-85d5-39064cf092bs", customer);
        List<ConfigurationDto> configurationSnapshot = createConfigurationList().stream()
                .map(configurationMapper::toDto)
                .toList();
        List<OrderProductDto> products = List.of(new OrderProductDto(1L, "ES-1234-2314", "Computer", BigDecimal.valueOf(4000),
                ProductType.COMPUTER, "Computer super", configurationSnapshot));
        OrderCartDto orderCartDto = new OrderCartDto("8d379dc8-af0f-4122-85d5-39064cf092bs", products, BigDecimal.valueOf(5000));

        cartClientMock.stubFor(WireMock.get("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs").willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(orderCartDto))
        ));
        cartClientMock.stubFor(WireMock.delete("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs").willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(orderCartDto))
        ));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createOrderCommand))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.status").value(OrderStatus.CREATED.toString()))
                .andExpect(jsonPath("$.total_price").value(BigDecimal.valueOf(5000)))
                .andExpect(jsonPath("$.products").isNotEmpty())
                .andExpect(jsonPath("$.created_at").isNotEmpty())
                .andExpect(jsonPath("$.updated_at").isNotEmpty());
        verify(1, getRequestedFor(urlEqualTo("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs")));
        verify(1, deleteRequestedFor(urlEqualTo("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs")));
    }

    private List<Configuration> createConfigurationList() {
        return List.of(new Configuration(1L, "ram_16", ConfigType.RAM,
                BigDecimal.valueOf(1000), "RAM 16GB", true));
    }
}