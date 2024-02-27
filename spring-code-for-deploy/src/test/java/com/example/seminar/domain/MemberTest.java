package com.example.seminar.domain;


import com.example.seminar.repository.MemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 정보를 입력하면 회원을 등록할 수 있다.")
    void memberSaveTest() {

        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        Member member = Member.builder()
                .age(99)
                .name("오해영")
                .sopt(sopt)
                .nickname("5hae0")
                .build();

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("age", "name", "sopt", "nickname")
                .containsExactly(99, "오해영", sopt, "5hae0");

    }
}
