package com.example.seminar.dto.response.category;

import com.example.seminar.domain.Category;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record CategoryResponse(
        String title
){

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .title(category.getTitle())
                .build();
    }
}
