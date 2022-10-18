package com.example.study_springboot.domain.feed.facade;

import com.example.study_springboot.domain.feed.domain.Feed;
import com.example.study_springboot.domain.feed.domain.repository.FeedRepository;
import com.example.study_springboot.domain.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedFacade {

    private final FeedRepository feedRepository;

    public Feed getFeed(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(()-> FeedNotFoundException.EXCEPTION);
    }

}
