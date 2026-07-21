package pl.javakurs.dname074.cart_service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureWireMock(port = 8015)
@Import(ContainerConfig.class)
public class ProductClientTest {
    @Autowired
    WireMockServer cartClientMock;
    @Autowired
    private ProductClient client;

    @BeforeEach
    void setup() {
        cartClientMock.resetRequests();
    }

    @Test
    void getCart_ResponseStatus503_3RetriesDone() {
        cartClientMock.stubFor(WireMock.get("/products/1")
                .willReturn(
                        aResponse()
                                .withStatus(503)
                ));
        assertThrows(feign.RetryableException.class, () -> client.getProduct(1L));
        verify(3, getRequestedFor(urlEqualTo("/products/1")));
    }
}
