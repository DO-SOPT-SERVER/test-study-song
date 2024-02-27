package com.example.seminar.domain;


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
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateContent(final String content) {
        this.content = content;
    }

}
