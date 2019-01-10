package pl.storeware.appModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EquationTest {

    @Test
    public void textRepresentationOfEquationShouldBeTheSame() {

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
        equation1.setStringRepresentation();

        //when computed
        String result = equation1.getStringRepresentation();

        //then
        String expected = "[(3.5+1.5)^2.0)/5.0)]";
        assertEquals(result, expected);
    }
}