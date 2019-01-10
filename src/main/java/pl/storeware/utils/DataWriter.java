package pl.storeware.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.storeware.appModel.MathOperator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class used to perform save operations. Its functionality is to save new equation in default text file.
 */

@Component
public class DataWriter {
    private final String WELCOME_MSG = "\n ############ " + "\n EQUATION CREATOR" + "\n Enter name of operation";

    File file = DataFile.getCALC_FILE();

    @Autowired
    DataReader dataReader;

    /**
     * Method creating new equation and write it to default file. Write operation overwriting existing content.
     * Communication with user is realized through Scanner.
     * User can enter String that is matching operators in MathOperator class followed by number.
     * APPLY command ends execution.
     * Input is case insensitive.
     */

    public void equationFromScanner() {
        System.out.println(WELCOME_MSG);
        Scanner scanner = new Scanner(System.in);

        List<String> operations = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("APPLY")) {
                System.out.println("You entered " + input + " which is final operation");
                Float number = dataReader.getFloat();
                String formula = input + " " + String.valueOf(number);
                operations.add(formula);
                saveToFile(operations);
                break;
            }
            else {
                try {
                    //1st step: get mathOperator
                    MathOperator mathOperator = MathOperator.valueOf(input);
                    System.out.println(mathOperator.toString() + " recognized");
                    //2nd step: get number
                    Float number = dataReader.getFloat();
                    System.out.println(number + "  entered sucessfully");
                    //3rd step: string concatenation
                    String formula = mathOperator.toString() + " " + String.valueOf(number);
                    //4th step: add to array
                    operations.add(formula);
                    System.out.println("Choose next operation");

                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown operator. Re-verify your input");
                }
            }
        }
    }

    /**
     * Method that takes as an input list of Strings and save it to default CALC_FILE
     * Writing operation overwrite existing content
     *
     * @param stringList
     */

    public void saveToFile(List<String> stringList) {
        try {
            FileWriter writer = new FileWriter(file, false);
            for (String s : stringList) {
                writer.append(s + "\n");
            }
            writer.close();
        }
        catch (IOException ex) {
            System.out.println("Error occured during saving. CalcOperation failed");
        }
    }

}
