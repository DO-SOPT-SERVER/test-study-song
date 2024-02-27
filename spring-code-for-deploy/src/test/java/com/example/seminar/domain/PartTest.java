package com.example.seminar.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PartTest {

    @Test
    @DisplayName("개발 파트를 반환할 수 있다.")
    void developParts() {
        Assertions.assertThat(Part.developParts())
                .isEqualTo(
                        List.of(
                                Part.SERVER,
                                Part.WEB,
                                Part.ANDROID,
                                Part.IOS
                        ));
    }
}
