package com.example.study_springboot.domain.user.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class UserExistException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserExistException();

    private UserExistException() {
        super(ErrorCode.USER_EXIST);
    }

}
