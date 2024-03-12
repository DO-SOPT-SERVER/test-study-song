package com.example.seminar.domain;


import com.example.seminar.common.exception.PostException;
import com.example.seminar.repository.PostJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostTest {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Test
    void given_정상입력경우_when_게시글등록_then_게시글등록성공(){
        // given
        Member member = Fixtures.createMember(25, "송민규", "송민규짱짱맨", Part.SERVER);
        Post post = Fixtures.createPost("정상제목입니다만", "정상내용입니다만", member);

        // when
        Post savedPost = postJpaRepository.save(post);

        // then
        Assertions.assertThat(savedPost)
                .extracting("title", "content", "member")
                .containsExactly(savedPost.getTitle(), savedPost.getContent(), savedPost.getMember());
    }

    @Test
    void given_게시글제목3자미만경우_when_게시글등록_then_예외발생(){
        // given
        Member member = Fixtures.createMember(25, "송민규", "송민규짱짱맨", Part.SERVER);

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Post post = Post.builder()
                            .title("미만")
                            .content("정상내용입니다")
                            .member(member)
                            .build();
                })
                .isInstanceOf(PostException.class)
                .hasMessageContaining("게시글 제목은 3자 미만이 될 수 없습니다.");
    }
}
