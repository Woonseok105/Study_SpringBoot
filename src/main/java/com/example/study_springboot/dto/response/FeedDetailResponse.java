package com.example.study_springboot.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedDetailResponse {

    private String title;
    private String content;

}
