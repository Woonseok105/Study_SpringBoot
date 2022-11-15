package com.example.study_springboot.infrastructure.feign;

import com.example.study_springboot.infrastructure.feign.exception.FeignBadRequestException;
import com.example.study_springboot.infrastructure.feign.exception.FeignExpiredTokenException;
import com.example.study_springboot.infrastructure.feign.exception.FeignForbiddenException;
import com.example.study_springboot.infrastructure.feign.exception.FeignUnAuthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

// feign 의 경우 200 <= response status < 300 일때만 정상이라고 판단하며 이외의 값에 대해서는 모두 Internal Error 500으로 처리한다.
//ErrorDecoder가 없어 Feign Client의 에러 메세지를 받아 사용자에게 해당 메세지를 전달해주기 위해서는 Error Decoder를 구현해야만 한다.
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) throws FeignException {

        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw FeignUnAuthorizedException.EXCEPTION;
                case 403:
                    throw FeignForbiddenException.EXCEPTION;
                case 419:
                    throw FeignExpiredTokenException.EXCEPTION;
                default:
                    throw FeignBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }

}
