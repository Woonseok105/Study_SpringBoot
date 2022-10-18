package com.example.study_springboot.domain.feed.exception;

import com.example.study_springboot.global.error.CustomException;
import com.example.study_springboot.global.error.ErrorCode;

public class FeedCannotDeleteException extends CustomException {

    public static final FeedCannotDeleteException EXCEPTION =
            new FeedCannotDeleteException();

    private FeedCannotDeleteException() {
        super(ErrorCode.FEED_CANNOT_DELETE);
    }

}
