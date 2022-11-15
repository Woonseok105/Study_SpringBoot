package com.example.study_springboot.domain.feed.service;

import com.example.study_springboot.domain.feed.presentation.dto.request.FeedRequest;
import com.example.study_springboot.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.example.study_springboot.domain.feed.domain.Feed;
import com.example.study_springboot.domain.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
//사용이유
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    @Transactional
    public String createFeed(FeedRequest feedRequest) {
        feedRepository.save(Feed.builder()
                .content(feedRequest.getContent())
                .title(feedRequest.getTitle())
                .build());
        return "success";
    }

    public FeedDetailResponse getFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return FeedDetailResponse.builder()
                .title(feed.getTitle())
                .content(feed.getContent())
                .build();
    }

    public void deleteFeed(Long feedId) {
        feedRepository.deleteById(feedId);
    }

    public void updateFeed(Long feedId, FeedRequest feedRequest) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new RuntimeException("게시글을 업데이트 할 수 없습니다."));
        feed.update(feedRequest.getTitle(), feedRequest.getContent());
    }

}


