package pl.storeware.appModel;

/**
 * Enum class collecting available mathematical operations in Calculator.
 */
public enum MathOperator {

    ADD ("+"),

    SUBTRACT ("-"),

    MULTIPLY ("*"),

    DIVIDE ("/"),

    POWER ("^");

    private String operator;

    public String getOperator() {
        return operator;
    }

    MathOperator(String symbol) {
        this.operator = symbol;
    }
}
