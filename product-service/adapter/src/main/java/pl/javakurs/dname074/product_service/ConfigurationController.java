package pl.javakurs.dname074.product_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.dto.ConfigurationDto;
import pl.javakurs.dname074.dto.CreateConfigurationCommand;
import pl.javakurs.dname074.dto.ExceptionResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/configurations")
class ConfigurationController {
    private final ConfigurationServiceFacade configurationService;
    private final ConfigurationMapper configurationMapper;

    @Operation(summary = "Add configuration option to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Configuration created",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ConfigurationDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Configuration with provided name and type already exists or configuration type doesn't match provided product's type",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Configuration can't be a product, because product with provided id doesn't exist",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponseDto.class))
            })
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ConfigurationDto addConfiguration(@RequestBody @Valid CreateConfigurationCommand configuration) {
        log.info("Received POST /configurations request with body: {}", configuration);
        return configurationMapper.toDto(configurationService.addConfiguration(configurationMapper.dtoToPojo(configuration), configuration.productId()));
    }
}
