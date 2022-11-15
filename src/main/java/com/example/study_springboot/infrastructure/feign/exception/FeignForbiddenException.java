package com.example.study_springboot.infrastructure.feign.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeignForbiddenException extends CustomException {

    public static final CustomException EXCEPTION =
            new FeignForbiddenException();

    private FeignForbiddenException() {
        super(ErrorCode.FEIGN_FORBIDDEN);
    }

}
