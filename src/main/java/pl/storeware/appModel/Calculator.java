package pl.storeware.appModel;

import org.springframework.stereotype.Component;
import pl.storeware.errors.Warning;
import java.util.List;

/**
 * Class performing calculation of the input delivered through Equation objects.
 */
@Component
public class Calculator implements CalcOperation {

    @Override
    public float addNumbers(float a, float b) {
        float result = a + b;
        return result;
    }

    @Override
    public float subtract(float a, float b) {
        float result = a - b;
        return result;
    }

    @Override
    public float divide(float a, float b) {
        if (b == 0.0) {
            throw new ArithmeticException();
        }
        float result = a / b;
        return result;
    }

    @Override
    public float multiply(float a, float b) {
        float result = a * b;
        return result;
    }

    @Override
    public float power(float a, float exponent) {
        float result = (float) Math.pow(a, exponent);
        return result;
    }

    /**
     * Method which calculates result of mathematical operations sotred in Equation object
     * It is final step of callculation.
     * Returned object has full data including base number, operators, modifiers and result.
     * In case of errors during computing method returns null.
     *
     * @param equation
     * @return equation
     */
    public Equation computeEquation(Equation equation) {
        float result;
        List<MathOperator> mathOperators = equation.getMathOperators();
        List<Float> modifiers = equation.getModifierNumbers();
        Float baseNumber = equation.getBaseNumber();

        if (mathOperators.size() == 0 && modifiers.size() == 0) {
            result = baseNumber;
            equation.setResult(result);
            equation.setStringRepresentation();
            return equation;
        } else {
            try {
                result = baseNumber;
                for (int i = 0; i < mathOperators.size(); i++) {
                    MathOperator o = mathOperators.get(i);
                    switch (o) {
                        case ADD:
                            result = addNumbers(result, modifiers.get(i));
                            break;
                        case SUBTRACT:
                            result = subtract(result, modifiers.get(i));
                            break;
                        case MULTIPLY:
                            result = multiply(result, modifiers.get(i));
                            break;
                        case DIVIDE:
                            try {
                                result = divide(result, modifiers.get(i));
                                break;
                            } catch (ArithmeticException e) {
                                System.out.println(Warning.DIV_BY_ZERO_ERROR);
                                result = Float.parseFloat(null);
                            }
                        case POWER:
                            result = power(result, modifiers.get(i));
                            break;
                    }
                }

                equation.setStringRepresentation();
                equation.setResult(result);
                return equation;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Warning.INDEX_OUT_OF_BOUND);
            }
        }
        return null;
    }
}
