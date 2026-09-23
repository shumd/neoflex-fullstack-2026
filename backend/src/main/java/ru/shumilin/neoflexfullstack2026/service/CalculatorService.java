package ru.shumilin.neoflexfullstack2026.service;

import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;

public interface CalculatorService {
    CalculationResponseDto calculate(CalculationRequestDto request);
}
