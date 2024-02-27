package org.unan.week1;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private static final Integer NULL_NUMBER = null;
    private static final Integer INVALID_NEGATIVE_NUMBER = -1;
    private static final Integer ONE = 1;


    @Test
    @DisplayName("입력 값을 넣으면 더한 값을 출력해준다.")
    void add() {
        DigitalCalculator calculator = new DigitalCalculator();
        int action = calculator.add(ONE,1);
        Assertions.assertThat(action).isEqualTo(2);
    }

    @Test
    @DisplayName("입력 값 중에 Null이 있으면, 계산이 이루어지지 않는다.")
    void addWithNullInput() {
        DigitalCalculator calculator = new DigitalCalculator();

        Assertions.assertThatThrownBy(
                () -> {calculator.add(NULL_NUMBER, ONE);}
        ).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("NULL");
    }

    @Test
    @DisplayName("입력 값을 더했을 때 10만이 넘으면 예외가 발생한다.")
    void addResultExceed100000() {
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(50000, 50001);}
        ).isInstanceOf(IllegalArgumentException.class);

    }

}