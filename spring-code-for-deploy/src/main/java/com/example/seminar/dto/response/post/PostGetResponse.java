package com.example.seminar.dto.response.post;

import com.example.seminar.domain.Category;
import com.example.seminar.domain.Post;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;


@Builder(access = PRIVATE)
public record PostGetResponse(
        Long id,
        String title,
        String content,
        String categoryTitle
) {
    public static PostGetResponse of(
            Post post,
            Category category) {
        return PostGetResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .categoryTitle(category.getTitle())
                .build();
    }
}
