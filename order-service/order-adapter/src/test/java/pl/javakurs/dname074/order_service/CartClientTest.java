package pl.javakurs.dname074.order_service;

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
public class CartClientTest {
    @Autowired
    WireMockServer cartClientMock;
    @Autowired
    private CartClient client;

    @BeforeEach
    void setup() {
        cartClientMock.resetRequests();
    }

    @Test
    void getCart_ResponseStatus503_3RetriesDone() {
        cartClientMock.stubFor(WireMock.get("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs")
                .willReturn(
                        aResponse()
                                .withStatus(503)
                ));
        assertThrows(feign.RetryableException.class, () -> client.getCart("8d379dc8-af0f-4122-85d5-39064cf092bs"));
        verify(3, getRequestedFor(urlEqualTo("/carts/8d379dc8-af0f-4122-85d5-39064cf092bs")));
    }
}
