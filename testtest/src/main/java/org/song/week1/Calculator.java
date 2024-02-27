package org.song.week1;

import java.util.Objects;

public abstract class Calculator {
    private CalculatorType calculatorType;

    public Calculator(CalculatorType calculatorType) {
        this.calculatorType = calculatorType;
    }

    public int add(Integer x, Integer y){
        inputNullCheck(x, y);

        return integerAndLimitCheck(x+y);
    }

    public int sub(Integer x, Integer y){
        inputNullCheck(x, y);

        return integerAndLimitCheck(x-y);
    }

    public int div(Integer dividend, Integer divisor){
        inputNullCheck(dividend, divisor);

        if(divisor == 0){
            throw new IllegalArgumentException("DIVISOR_ZERO");
        }

        return integerAndLimitCheck((double) dividend / divisor);
    }

    public int mul(Integer x, Integer y){
        inputNullCheck(x, y);

        return integerAndLimitCheck(x*y);
    }

    protected void inputNullCheck(Integer x, Integer y){
        if(Objects.isNull(x) || Objects.isNull(y)){
            throw new IllegalArgumentException("NULL");
        }
    }

    protected int integerAndLimitCheck(double result){
        if(result != (int) result){
            throw new IllegalArgumentException("NOT_INTEGER");
        }

        if(result>100000 || result<0){
            throw new IllegalArgumentException("LIMIT_EXCEEDED");
        }

        return (int) result;
    }

}
