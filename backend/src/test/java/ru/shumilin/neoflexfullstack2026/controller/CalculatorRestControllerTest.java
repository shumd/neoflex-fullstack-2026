package ru.shumilin.neoflexfullstack2026.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.shumilin.neoflexfullstack2026.dto.CalculationRequestDto;
import ru.shumilin.neoflexfullstack2026.dto.CalculationResponseDto;
import ru.shumilin.neoflexfullstack2026.service.CalculatorService;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorRestControllerTest {

    @MockitoBean
    private CalculatorService calculatorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void calculate_withValidRequestBody_returnCalculationResponseDto() {
        when(calculatorService.calculate(any()))
                .thenReturn(getCalculationResponseDto());

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(null, null, null)
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(108300.50))
                .andExpect(jsonPath("$.profit").value(8300.50));
    }

    @Test
    @SneakyThrows
    public void calculate_withAmountLessThanMinimum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        new BigDecimal("999"),
                                        null,
                                        null
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withAmountGreaterThanMaximum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        new BigDecimal("10000001"),
                                        null,
                                        null
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withMonthsLessThanMinimum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        null,
                                        0,
                                        null
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withMonthsGreaterThanMaximum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        null,
                                        61,
                                        null
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withRateLessThanMinimum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        null,
                                        null,
                                        new BigDecimal("0.9")
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withRateGreaterThanMaximum_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(
                                        null,
                                        null,
                                        new BigDecimal("20.1")
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withNullAmount_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CalculationRequestDto(
                                        null,
                                        12,
                                        new BigDecimal("8.5")
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withNullMonths_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CalculationRequestDto(
                                        new BigDecimal("100000"),
                                        null,
                                        new BigDecimal("8.5")
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withNullRate_return400HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CalculationRequestDto(
                                        new BigDecimal("100000"),
                                        12,
                                        null
                                )
                        )))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_withWrongAccept_return406HttpCode() {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_PDF)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(null, null, null)
                        )))
                .andExpect(status().isNotAcceptable());

        verifyNoInteractions(calculatorService);
    }

    @Test
    @SneakyThrows
    public void calculate_whenServerError_return500HttpCode() {
        when(calculatorService.calculate(any()))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getCalculationRequestDto(null, null, null)
                        )))
                .andExpect(status().isInternalServerError());
    }

    private CalculationRequestDto getCalculationRequestDto(
            BigDecimal amount,
            Integer months,
            BigDecimal rate
    ) {
        return new CalculationRequestDto(
                amount == null ? new BigDecimal("100000") : amount,
                months == null ? 12 : months,
                rate == null ? new BigDecimal("8.5") : rate
        );
    }

    private CalculationResponseDto getCalculationResponseDto() {
        return new CalculationResponseDto(
                new BigDecimal("108300.50"),
                new BigDecimal("8300.50")
        );
    }
}