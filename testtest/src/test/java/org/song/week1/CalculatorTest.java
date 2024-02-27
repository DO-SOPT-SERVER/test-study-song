package org.song.week1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    // 실패 조건에 집중해서 작성해보자.
    private DigitalCalculator digitalCalculator;
    private EngineeringCalculator engineeringCalculator;

    @BeforeEach
    void init(){
        digitalCalculator = new DigitalCalculator();
        engineeringCalculator = new EngineeringCalculator();
    }

    @Test
    @DisplayName("입력 값을 더했을 때 10만이 넘을 경우 예외가 발생한다.")
    void When_addResultExceed100k_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.add(50000, 50001);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값을 더했을 때 0보다 작을 경우 예외가 발생한다.")
    void When_addResultLessThen0_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.add(-1, 0);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값에 공백이 있을 때 예외가 발생한다.")
    void When_addWithNullInput_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.add(null, 15);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값을 나눴을 때 정수가 아니면 예외가 발생한다.")
    void When_divideResultIsNotInteger_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.div(10, 3);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값을 나눴을 때 나누는 수가 0일 경우에 예외가 발생한다.")
    void When_divideWithDivisorIsZero_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.div(10, 0);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직각 삼각형 넓이를 구할 때 입력값에 음수가 있을 경우 예외가 발생한다.")
    void Given_negativeInput_When_calculateAreaRightTriangle_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {engineeringCalculator.calculateAreaRightTriangle(10, -1);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직각 삼각형 넓이가 정수가 아닐 경우 예외가 발생한다.")
    void When_RightTriangleAreaIsNotInteger_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {engineeringCalculator.calculateAreaRightTriangle(5, 7);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값에 공백이 있을 때 뺄셈을 하는 경우 예외가 발생한다.")
    void When_subWithNullInput_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.sub(null, 12);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값을 뺄셈했을 때 0보다 작을 경우 예외가 발생한다.")
    void When_subResultLessThen0_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.sub(10, 40);}
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값에 공백이 있을 때 곱셈을 하는 경우 예외가 발생한다.")
    void When_mulWithNullInput_Then_throwException(){
        Assertions.assertThatThrownBy(
                () -> {digitalCalculator.mul(null, 65);}
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
