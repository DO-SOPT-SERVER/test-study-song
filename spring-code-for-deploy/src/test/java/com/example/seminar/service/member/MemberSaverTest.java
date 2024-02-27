package com.example.seminar.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MemberSaverTest {

    @Autowired
    private MemberRegister memberRegister;

    @Test
    @DisplayName("SOPT 회원을 등록할 수 있다.")
    void register() {
      // given

      // when

      // then

    }

    @Test
    @DisplayName("새롭게 등록한 SOPT 회원은 현재 기수이다.")
    void checkIsCurrentGeneration() {

    }
}
