package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
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
        Member member = Fixtures.createMember(25, "송민규", "송민규짱짱맨", Part.SERVER);

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("age", "name", "sopt", "nickname")
                .containsExactly(member.getAge(), member.getName(), member.getSopt(), member.getNickname());
    }

    @Test
    void given_회원나이100세초과할경우_when_회원등록_then_예외발생() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(101)
                            .name("오해영")
                            .sopt(sopt)
                            .nickname("5hae0")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessageContaining("회원의 나이는 0세 이상 100세 이하입니다.");
    }

    @Test
    void given_회원이름12자넘는경우_when_회원등록_then_예외발생() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(99)
                            .name("오해영또오해영또오해영오해")
                            .sopt(sopt)
                            .nickname("5hae0")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessageContaining("유저의 이름은 12자를 넘을 수 없습니다.");
    }

    @Test
    void given_회원닉네임null인경우_when_회원등록_then_예외발생() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(99)
                            .name("오해영")
                            .sopt(sopt)
                            .nickname(null)
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessageContaining("null 값이 될 수 없습니다.");

    }

    @Test
    void given_회원이름null인경우_when_회원등록_then_예외발생() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(99)
                            .name(null)
                            .sopt(sopt)
                            .nickname("오해영닉네임")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessageContaining("null 값이 될 수 없습니다.");

    }


}
