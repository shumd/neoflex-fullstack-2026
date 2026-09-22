package ru.shumilin.neoflexfullstack2026.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;
import ru.shumilin.neoflexfullstack2026.dto.ErrorResponseDto;

import static ru.shumilin.neoflexfullstack2026.util.ErrorTitleConstant.INTERNAL_SERVER_ERROR;
import static ru.shumilin.neoflexfullstack2026.util.ErrorTitleConstant.NOT_ACCEPTABLE;

@Tag(
        name = "calculator-controller",
        description = "Взаимодействие с калькулятором"
)
public interface CalculatorAPI {
    @Operation(
            summary = "Расчёт итоговой суммы вклада"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Расчёт успешно выполнен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CalculationResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "406",
                    description = NOT_ACCEPTABLE,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = INTERNAL_SERVER_ERROR,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    ResponseEntity<CalculationResponseDto> calculate(
            CalculationRequestDto request
    );
}
