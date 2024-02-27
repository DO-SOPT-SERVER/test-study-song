package com.example.seminar.service.post;

import com.example.seminar.domain.Post;
import com.example.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRemover {

    private final PostJpaRepository postJpaRepository;

    public void remove(final Post post) {
        postJpaRepository.delete(post);
    }
}
