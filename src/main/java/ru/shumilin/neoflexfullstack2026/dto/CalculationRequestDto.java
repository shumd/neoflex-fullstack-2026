package ru.shumilin.neoflexfullstack2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "Запрос на вычисление вклада")
public record CalculationRequestDto(
        @Schema(
                description = "Сумма",
                example = "100000",
                minimum = "1000",
                maximum = "10000000"

        )
        @DecimalMin("1000")
        @DecimalMax("10000000")
        @NotNull
        BigDecimal amount,

        @Schema(
                description = "Срок в месяцах",
                example = "12",
                minimum = "1",
                maximum = "60"

        )
        @Min(1)
        @Max(60)
        @NotNull
        Integer months,

        @Schema(
                description = "Годовая процентная ставка",
                example = "8.5",
                minimum = "1",
                maximum = "20"
        )
        @DecimalMin("1")
        @DecimalMax("20")
        @NotNull
        BigDecimal rate
) {
}