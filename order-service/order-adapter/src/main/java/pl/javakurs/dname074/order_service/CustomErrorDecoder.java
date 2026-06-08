package pl.javakurs.dname074.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.order.model.exception.ExternalClientException;
import pl.javakurs.dname074.order.dto.ExceptionResponseDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionResponseDto errorResponse = extractBody(response);

        Integer errorCode = Optional.ofNullable(errorResponse)
                .map(ExceptionResponseDto::statusCode)
                .orElse(response.status());

        String message = Optional.ofNullable(errorResponse)
                .map(ExceptionResponseDto::message)
                .orElse("No error details available");

        log.error("Feign error [{}] - status: {}, code: {}, message: {}",
                methodKey, response.status(), errorCode, message);

        return switch (response.status()) {
            case 500 -> new ExternalClientException("Internal server error: " + message, 500);
            case 503 -> new RetryableException(
                    response.status(),
                    "Service unavailable: " + message,
                    response.request().httpMethod(),
                    null,
                    100L,
                    response.request()
            );
            default -> new ExternalClientException(message, errorCode);
        };
    }

    private ExceptionResponseDto extractBody(Response response) {
        if (response.body() == null) {
            return null;
        }
        try (InputStream bodyStream = response.body().asInputStream()){
            return objectMapper.readValue(bodyStream, ExceptionResponseDto.class);
        } catch (IOException e) {
            log.warn("Could not deserialize Feign error response body: {}", e.getMessage());
            return null;
        }
    }
}
