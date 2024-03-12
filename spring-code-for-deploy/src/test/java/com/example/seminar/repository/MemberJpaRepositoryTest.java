package com.example.seminar.repository;

import com.example.seminar.domain.Fixtures;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    PostJpaRepository postJpaRepository;

    @Test
    void given_정상입력경우_when_회원등록_then_회원등록성공() {
        // given
        Member member = Fixtures.createMember(25, "송민규", "송민규짱짱맨", Part.SERVER);

        // when
        Member savedMember = memberJpaRepository.saveAndFlush(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("name", "nickname", "age")
                .containsExactly(member.getName(), member.getNickname(), member.getAge());
    }

    @Test
    void given_정상입력경우_when_회원및게시글등록_then_회원및게시글등록성공() {
        // given
        Member member = Fixtures.createMember(11, "송민규", "송민규입니다만", Part.SERVER);

        Post post1 = Post.builder()
                .member(member)
                .title("포스팅제목")
                .content("이것은 내용입니당")
                .build();
        Post post2 = Post.builder()
                .member(member)
                .title("포스팅제목22")
                .content("이것은 내용입니당22")
                .build();

        member.addPosts(post1);
        member.addPosts(post2);

        // when
        Member savedMember = memberJpaRepository.saveAndFlush(member);

        // then
        for(Post post : savedMember.getPosts()){
            Assertions.assertThat(post.getMember())
                    .extracting("name", "nickname", "age")
                    .containsExactly("송민규", "송민규입니다만", 11);
        }

    }
}
