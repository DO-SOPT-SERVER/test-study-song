package com.example.seminar.service.post;


import com.example.seminar.common.exception.PostException;
import com.example.seminar.domain.Post;
import com.example.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRetriever {

    private final PostJpaRepository postJpaRepository;

    public Post findById(final long id) {
        return postJpaRepository.findById(id)
                .orElseThrow(() -> new PostException("게시글이 존재하지 않습니다."));
    }
}
