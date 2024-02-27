package org.song.week1;

public class EngineeringCalculator extends Calculator{
    public EngineeringCalculator() {
        super(CalculatorType.ENGINEERING);
    }

    public int calculateAreaRightTriangle(Integer x, Integer y){
        inputNullCheck(x, y);

        if(x < 0 || y < 0){
            throw new IllegalArgumentException("NEGATIVE");
        }

        return integerAndLimitCheck((double) x*y/2);
    }

}