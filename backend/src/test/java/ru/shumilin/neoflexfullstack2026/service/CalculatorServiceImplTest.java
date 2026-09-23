package ru.shumilin.neoflexfullstack2026.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;

import java.math.BigDecimal;

class CalculatorServiceImplTest {
    private final CalculatorServiceImpl calculatorService =
            new CalculatorServiceImpl();

    @Test
    void calculate_whenValidRequest_returnCorrectTotalAndProfit() {
        CalculationRequestDto request = new CalculationRequestDto(
                new BigDecimal("100000"),
                12,
                new BigDecimal("8.5")
        );

        CalculationResponseDto expected = new CalculationResponseDto(
                new BigDecimal("108839.09"),
                new BigDecimal("8839.09")
        );

        Assertions.assertEquals(
                expected,
                calculatorService.calculate(request)
        );
    }
}