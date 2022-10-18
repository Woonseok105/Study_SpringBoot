package com.example.study_springboot.domain.auth.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class RefreshTokenNotFoundException extends CustomException {

    public static final RefreshTokenNotFoundException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }

}
