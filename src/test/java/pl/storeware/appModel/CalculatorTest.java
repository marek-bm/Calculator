package pl.storeware.appModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {

    @Test(expected = ArithmeticException.class)
    public void shoulThrowExceptionWhenDividedByZero() {
        Calculator calculator = new Calculator();
        float result = calculator.divide(10, 0);

    }

    @Test
    public void equationResultShouldBeCalculatedCorrectly() {
        Calculator calculator = new Calculator();

        //given equation
        Equation equation1 = new Equation();
        equation1.setBaseNumber(3.5f);

        List<MathOperator> operatorsEq1 = new ArrayList<>();
        operatorsEq1.add(MathOperator.ADD);
        operatorsEq1.add(MathOperator.POWER);
        operatorsEq1.add(MathOperator.DIVIDE);
        equation1.setMathOperators(operatorsEq1);

        List<Float> modifiers = new ArrayList<>();
        modifiers.add(1.5f);
        modifiers.add(2f);
        modifiers.add(5f);
        equation1.setModifierNumbers(modifiers);

        //when computed
        calculator.computeEquation(equation1);

        //then
        float expected = 5f;
        float result = equation1.getResult();

        assertEquals(expected, result, 0.01);
    }
}