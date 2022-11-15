package com.example.study_springboot.infrastructure.feign.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeignExpiredTokenException extends CustomException {

    public static final CustomException EXCEPTION =
            new FeignExpiredTokenException();

    private FeignExpiredTokenException() {
        super(ErrorCode.FEIGN_EXPIRED_TOKEN);
    }

}
