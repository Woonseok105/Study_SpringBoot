package com.example.study_springboot.domain.feed.presentation;

import com.example.study_springboot.domain.feed.presentation.dto.request.FeedRequest;
import com.example.study_springboot.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.example.study_springboot.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public String createFeed(@RequestBody @Valid FeedRequest request) {
        return feedService.createFeed(request);
    }

    @GetMapping("/{feed-id}")
    public FeedDetailResponse getFeed(@PathVariable("feed-id") Long feedId) {
        return feedService.getFeed(feedId);
    }


    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
         feedService.deleteFeed(feedId);
    }

    @PatchMapping("/{feed-id}")
    public void updateFeed(@PathVariable("feed-id") Long feedId, @RequestBody FeedRequest feedRequest) {
        feedService.updateFeed(feedId, feedRequest);
    }
}
