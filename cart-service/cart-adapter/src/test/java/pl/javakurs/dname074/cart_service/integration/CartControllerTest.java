//package pl.javakurs.dname074.cart_service.integration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.tomakehurst.wiremock.WireMockServer;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.web.servlet.MockMvc;
//import pl.javakurs.dname074.cart_service.CartRepository;
//import pl.javakurs.dname074.cart_service.ContainerConfig;
//
//@SpringBootTest
//@AutoConfigureWireMock(port = 8082)
//@AutoConfigureMockMvc
//@Import(ContainerConfig.class)
//public class CartControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    WireMockServer productClientMock;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    CartRepository repository;
//
//    @BeforeEach
//    void setup() {
//
//    }
//}
