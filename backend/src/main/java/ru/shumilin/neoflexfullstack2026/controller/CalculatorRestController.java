package ru.shumilin.neoflexfullstack2026.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;
import ru.shumilin.neoflexfullstack2026.service.CalculatorService;

@RestController
@RequestMapping("/api/calculate")
@RequiredArgsConstructor
public class CalculatorRestController implements CalculatorAPI {
    private final CalculatorService calculatorService;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationResponseDto> calculate(@RequestBody @Valid CalculationRequestDto request){
        return ResponseEntity.ok(calculatorService.calculate(request));
    }
}
