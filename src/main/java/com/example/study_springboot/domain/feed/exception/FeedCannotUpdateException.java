package com.example.study_springboot.domain.feed.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeedCannotUpdateException extends CustomException {

    public static final FeedCannotUpdateException EXCEPTION =
            new FeedCannotUpdateException();

    private FeedCannotUpdateException() {
        super(ErrorCode.FEED_CANNOT_UPDATE);
    }

}
