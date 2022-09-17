package com.example.study_springboot.domain.feed.presentation.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class FeedRequest {

    @NotNull
    @Size(max = 20, message = "title은 최대 20자입니다.")
    private String title;

    @NotNull
    @Size(max = 500, message = "content는 최대 500자입니다.")
    private String content;


}
