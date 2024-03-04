package com.example.seminar.repository;

import com.example.seminar.domain.Fixtures;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostJpaRepositoryTest {
    @Autowired
    PostJpaRepository postJpaRepository;

    @Test
    void given_정상입력경우_when_게시글등록_then_게시글등록성공() {
        // given
        Member member = Fixtures.createMember(25, "송민규", "송송이라");
        Post post = Fixtures.createPost("정상제목", "정상내용입니다.", member);

        // when
        Post savedPost = postJpaRepository.save(post);

        // then
        Assertions.assertThat(savedPost)
                .extracting("title", "content", "member")
                .containsExactly(post.getTitle(), post.getContent(), member);
    }
}
