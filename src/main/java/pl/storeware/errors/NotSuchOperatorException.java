package pl.storeware.errors;


/**
 * Exception to communicate that String conversion to MathOperator object failed
 * or there is not any corresponding operator in MathOperator class
 */
public class NotSuchOperatorException extends RuntimeException {

    public NotSuchOperatorException(String message) {
        super(message);
    }

}
