package org.unan.week1;


import java.util.Objects;

// 계산기 유형이 DIGITAL, ENGINEERING
public abstract class Calculator {
    private CalculatorType type;

    public Calculator(CalculatorType type) {
        this.type = type;
    }

    public int add(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            throw new IllegalArgumentException("NULL");
        }

        if (x+y > 100000) {
            throw new IllegalArgumentException("LIMIT");
        }
        return x+y;
    }

    public int subtract(Integer x, Integer y) {
        return x-y;
    }
}
