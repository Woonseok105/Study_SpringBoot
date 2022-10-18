package com.example.study_springboot.domain.feed.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeedNotFoundException extends CustomException {

    public static final FeedNotFoundException EXCEPTION =
            new FeedNotFoundException();

    private FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }

}
