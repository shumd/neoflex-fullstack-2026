package ru.shumilin.neoflexfullstack2026.excepiton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shumilin.neoflexfullstack2026.dto.ErrorResponseDto;

import static ru.shumilin.neoflexfullstack2026.util.ErrorTitleConstant.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e){
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponseDto(NOT_ACCEPTABLE));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        log.warn(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleBase(Exception e){
        log.warn(e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDto(INTERNAL_SERVER_ERROR));
    }
}
