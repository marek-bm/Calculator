package pl.storeware.appModel;

/**
 * Interfece specyfing mathematical operations
 */
public interface CalcOperation {

    float addNumbers(float a, float b);

    float subtract(float a, float b);

    float divide(float a, float b) throws ArithmeticException;

    float multiply(float a, float b);

    float power(float a, float exponent);
}
