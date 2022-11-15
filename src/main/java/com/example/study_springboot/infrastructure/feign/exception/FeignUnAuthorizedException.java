package com.example.study_springboot.infrastructure.feign.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeignUnAuthorizedException extends CustomException {

    public static final CustomException EXCEPTION =
            new FeignUnAuthorizedException();

    private FeignUnAuthorizedException() {
        super(ErrorCode.FEIGN_UN_AUTHORIZED);
    }

}
