package com.wkrzyz.controller;

import com.wkrzyz.dto.ErrorDTO;
import com.wkrzyz.exception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
/**
 * Custom error handling controller
 *
 */
@ControllerAdvice
public class ControllerAdvisor {
    /**
     * This method handles the NotFoundException
     * @param exception the exception that was caught
     */
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException exception){
        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build());
    }
    /**
     * This method handles the MethodArgumentNotValidException
     * @param exception the exception that was caught
     * */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .message(exception.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(" and ")))
                .time(LocalDateTime.now())
                .build());
    }
}
