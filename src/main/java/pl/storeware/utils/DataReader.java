package pl.storeware.utils;

import org.springframework.stereotype.Component;
import pl.storeware.appModel.Equation;
import pl.storeware.appModel.MathOperator;
import pl.storeware.errors.NotSuchOperatorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class functionality:
 * - read mathematical operations stored in default file
 * - build Equation object from data stored in text file
 */
@Component
public class DataReader {
    private static String WARNING = "File not found. Please make sure that 'calc.txt' is in project directory";


    /**
     * Method takes as an input file and prints out the text to console
     * @param file java.io.File
     */
    public void getFilePreview(File file) {

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("\n******************");
            System.out.println(file.getName() + " containts following instructions:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            System.out.println("******************");
        } catch (FileNotFoundException e) {
            System.out.println(WARNING);
        }
    }


    /**
     * Method takes file as an input and generates Equation.class object.
     * Generated object is required to finalize calculation in Calculator.class.
     * Method throws two exception when file contain wrong text format:
     * - NotSuchOperatorException when mathematical operator has not been recognized,
     * - NumberFormatException when text to number conversion fails.
     *
     * @param file java.io.File;
     * @return equation pl.storeware.appLogic.Equation
     */
    public Equation getEquation(File file) {

        Equation equation = new Equation();
        List<MathOperator> mathOperators = new ArrayList<>();
        List<Float> modifires = new ArrayList<>();


        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.isEmpty()) {
                    continue;
                } else if (line.contains("apply".toUpperCase())) {
                    String[] split = line.split(" ");
                    Float baseNumber = Float.parseFloat(split[1]);
                    equation.setBaseNumber(baseNumber);
                    break;
                } else {
                    String[] split = line.split(" ");

                    try {
                        MathOperator mathOperator = extractOperator(split[0]);
                        mathOperators.add(mathOperator);

                        Float modifier = exctractModifier(split[1]);
                        modifires.add(modifier);


                    } catch (NotSuchOperatorException e) {
                        String OPERATOR_ERROR = "MathOperator colud not be properly identified. Please verify spelling \n" + "Further operations cannot be continued";
                        System.out.println("Error occured at: " + split[0] + "\n");
                        System.out.println(OPERATOR_ERROR);
//                        break;
                        throw new NotSuchOperatorException(OPERATOR_ERROR);

                    } catch (NumberFormatException e) {
                        String CONVERSION_ERROR = "Could not execute number conversion \n" + "Further operations cannot be continued";
                        System.out.println(CONVERSION_ERROR + "\n" + "Occured at: " + split[1]);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(WARNING);
        }

        equation.setMathOperators(mathOperators);
        equation.setModifierNumbers(modifires);
        return equation;
    }


    /**
     * Class private returning MathOperator object.
     * It takes as an input String str and matches it to enum MathOperator.
     * Throws NotSuchOperatorException when matching operator is not found.
     *
     * @param str
     * @return MathOperator  pl.storeware.appLogic.MathOperator
     * @throws NotSuchOperatorException
     */
    private MathOperator extractOperator(String str) throws NotSuchOperatorException {

        MathOperator mathOperator;

        if (str.toUpperCase().equals("ADD")) {
            mathOperator = MathOperator.ADD;
        } else if (str.toUpperCase().equals("SUBTRACT")) {
            mathOperator = MathOperator.SUBTRACT;
        } else if (str.toUpperCase().equals("MULTIPLY")) {
            mathOperator = MathOperator.MULTIPLY;
        } else if (str.toUpperCase().equals("DIVIDE")) {
            mathOperator = MathOperator.DIVIDE;
        } else if (str.toUpperCase().equals("POWER")) {
            mathOperator = MathOperator.POWER;
        } else {
            throw new NotSuchOperatorException("MathOperator colud not be properly identified");
        }
        return mathOperator;
    }

    /**
     * Class private method. Takes as an input String str to be converted to float number.
     * Throws NumberFormatException when conversion fails.
     *
     * @param str
     * @return float number
     * @throws NumberFormatException
     */


    private Float exctractModifier(String str) throws NumberFormatException {

        Float number = Float.parseFloat(str);
        return number;
    }


    /**
     * Method using scanner to communicate with user
     * It accepts only int input to navigate Calculator menu
     *
     * @return int
     */
    public int getInt() {
        System.out.println("Choose option");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("wrong input, try again");
            scanner.next();
        }
        int number = scanner.nextInt();
        return number;
    }

    /**
     * Method using scanner to communicate with user
     * Its purpose is to get float number used in mathematical calculations
     *
     * @return float number
     */

    public float getFloat() {
        System.out.println("Enter number you want to use in calcualtion");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextFloat()) {
            System.out.println("Wrong input, try again");
            scanner.next();
        }
        float number = scanner.nextFloat();
        return number;
    }
}
