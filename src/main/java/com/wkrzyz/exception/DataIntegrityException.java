package com.wkrzyz.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataIntegrityException extends RuntimeException{
    private final String message;

    public String getMessage() {
        return this.message;
    }

}
