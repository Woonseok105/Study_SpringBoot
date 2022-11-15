package com.example.study_springboot.infrastructure.feign.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeignBadRequestException extends CustomException {

    public static final CustomException EXCEPTION =
            new FeignBadRequestException();

    private FeignBadRequestException() {
        super(ErrorCode.FEIGN_BAD_REQUEST);
    }

}
