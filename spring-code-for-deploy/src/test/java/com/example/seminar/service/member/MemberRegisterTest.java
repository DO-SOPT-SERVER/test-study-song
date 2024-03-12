package com.example.seminar.service.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import com.example.seminar.domain.Fixtures;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.repository.MemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class MemberRegisterTest {

    private static final Member MEMBER = Fixtures.createMember(20, "민규", "MG", Part.SERVER);
    private static final short CURRENT_GENERATION = 34;


    @InjectMocks
    private MemberRegister memberRegister;

    @Mock
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("[BDDMockito] SOPT 회원을 등록할 수 있다.")
    void register() {
        // given
        given(memberJpaRepository.save(any(Member.class))).willReturn(MEMBER);

        // when
        Member savedMember = memberRegister.register(MEMBER);

        // then
        Assertions.assertThat(savedMember)
                .extracting("age", "name", "nickname", "sopt")
                .containsExactly(MEMBER.getAge(), MEMBER.getName(), MEMBER.getNickname(), MEMBER.getSopt());

    }

    @Test
    @DisplayName("[BDDMockito] 새롭게 등록한 SOPT 회원은 현재 기수이다.")
    void checkIsCurrentGeneration() {
        // given
        given(memberJpaRepository.save(any(Member.class))).willReturn(MEMBER);

        // when
        Member savedMember = memberRegister.register(MEMBER);

        //then
        Assertions.assertThat(savedMember.getSopt().getGeneration()).isEqualTo(CURRENT_GENERATION);
    }
}
