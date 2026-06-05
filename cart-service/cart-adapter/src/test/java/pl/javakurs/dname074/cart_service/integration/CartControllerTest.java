package pl.javakurs.dname074.cart_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureWireMock(port = 8082)
@AutoConfigureMockMvc
public class CartControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    WireMockServer productClientMock;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
}
