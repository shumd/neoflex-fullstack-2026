package ru.shumilin.neoflexfullstack2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import static ru.shumilin.neoflexfullstack2026.util.ErrorTitleConstant.INTERNAL_SERVER_ERROR;

@Schema(description = "Ответ с информацией об ошибке")
public record ErrorResponseDto(

        @Schema(description = "Информация об ошибке", example = INTERNAL_SERVER_ERROR)
        String message
) {
}
