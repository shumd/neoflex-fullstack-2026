package ru.shumilin.neoflexfullstack2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Результат расчёта")
public record CalculationResponseDto(
        @Schema(
                description = "Итоговая сумма",
                example = "108300.50"
        )
        @DecimalMin("0")
        @NotNull
        BigDecimal total,

        @Schema(
                description = "Прибыль",
                example = "8300.50"
        )
        @DecimalMin("0")
        @NotNull
        BigDecimal profit
) {
}
