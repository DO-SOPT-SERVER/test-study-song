package com.example.seminar.domain;


import com.example.seminar.common.exception.CategoryException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category {

    private final int MAX_TITLE_LENGTH = 10;

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String title;

    @Builder
    private Category(String title) {
        validateContent(title);
        this.title = title;
    }

    private void validateContent(final String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new CategoryException("카테고리 글자 제한을 넘었습니다.");
        }
    }
}
