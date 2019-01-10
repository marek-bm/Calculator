package pl.storeware.appModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.*;

/**
 * POJO class representing Equation object.
 * The object has:
 * - baseNumber - number from which calculation is initialized
 * - mathOperators - operators which specify type of operation to be executed  on base number(or result in case of aggregate operations)
 * - modifierNumbers - numbers which completes the calculation
 * - result where outcome of calculation is stored
 * - stringRepresentation - String storing equation in mathematical format
 */
@Component
public class Equation {
    @Getter
    @Setter
    private float baseNumber;

    @Getter
    @Setter
    private List<Float> modifierNumbers;

    @Getter
    @Setter
    private List<MathOperator> mathOperators;

    @Getter
    private String stringRepresentation;

    @Getter
    @Setter
    private float result;

    @Override
    public String toString() {
        return "Equation{" + "baseNumber=" + baseNumber + ", modifierNumbers=" + modifierNumbers + ", mathOperators=" + mathOperators + ", stringRepresentation='" + stringRepresentation + '\'' + ", result=" + result + '}';
    }

    /**
     * Method converting CALC_FILE file output to String representation of mathematical format.
     * For example:
     * ADD 10
     * APPLY 2
     * will be translated to (10+2)
     */
    public void setStringRepresentation() {
        float baseNumber = this.getBaseNumber();

        if (this.getModifierNumbers().isEmpty() && this.getMathOperators().isEmpty()) {
            this.stringRepresentation = valueOf(baseNumber);
        } else {
            List<MathOperator> mathOperators = this.getMathOperators();
            List<Float> modifiers = this.getModifierNumbers();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[(" + this.getBaseNumber());

            for (int i = 0; i < mathOperators.size(); i++) {
                String operator = mathOperators.get(i).getOperator();
                stringBuilder.append(operator);

                String modifier = valueOf(modifiers.get(i));
                stringBuilder.append(modifier);
                stringBuilder.append(")");
            }

            stringBuilder.append("]");
            this.stringRepresentation = stringBuilder.toString();
        }
    }


    public void previevResult() {
        System.out.println("*****************");
        System.out.println("Result = " + this.getResult());
        System.out.println("*****************\n");
    }
}
