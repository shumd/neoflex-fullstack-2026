package ru.shumilin.neoflexfullstack2026.service;

import org.springframework.stereotype.Service;
import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public CalculationResponseDto calculate(CalculationRequestDto request) {
        BigDecimal monthlyRate = request.rate()
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal total = request.amount()
                .multiply(
                        BigDecimal.ONE
                                .add(monthlyRate)
                                .pow(request.months())
                )
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal profit = total
                .subtract(request.amount())
                .setScale(2, RoundingMode.HALF_UP);

        return new CalculationResponseDto(total, profit);

    }
}
