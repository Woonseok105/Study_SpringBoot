package com.example.study_springboot.repository;

import com.example.study_springboot.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    Optional<Feed> findById(Long feedId);
    //Optional : NullPointerException이 터지지 않도록 도와준다.
    //Optional이 있으면 orElseThrow를 사용할 수 있음 (서비스에서 사용함)
}

