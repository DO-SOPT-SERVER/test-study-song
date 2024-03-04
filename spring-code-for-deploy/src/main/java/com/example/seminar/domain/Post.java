package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import com.example.seminar.common.exception.PostException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    private final static int MIN_LENGTH = 3;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, Member member) {
        validateTitle(title);
        this.title = title;
        this.content = content;
        this.member = member;
    }

    private void validateTitle(final String title) {
        if (title.length() < MIN_LENGTH) {
            throw new PostException("게시글 제목은 3자 미만이 될 수 없습니다.");
        }
    }

    public void updateMember(Member member){
        this.member = member;
    }

    public void updateContent(final String content) {
        this.content = content;
    }

}
