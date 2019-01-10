package pl.storeware.appControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.storeware.appModel.Calculator;
import pl.storeware.appModel.Equation;
import pl.storeware.utils.DataFile;
import pl.storeware.utils.DataReader;
import pl.storeware.utils.DataWriter;
import pl.storeware.utils.WelcomeLogo;
import java.io.File;

import static pl.storeware.appControl.PanelOption.createFromInt;
import static pl.storeware.utils.WelcomeLogo.*;

/**
 * Class providing user interface to control Calculator
 */
@Component
public class ControlPanel {
    private PanelOption panelOption;

    @Autowired
    private DataReader dataReader;

    @Autowired
    private Calculator calculator;

    @Autowired
    private DataWriter dataWriter;

    /**
     * Method launching interface to control Calculator
     * Scanner inputs are converted to PanelOption enum and based on that another methods are launched
     */
    public void runControlLoop() {
        try {
            printLogo();
            printMenu();
            while ((panelOption = createFromInt(dataReader.getInt())) != PanelOption.EXIT) {
                switch (panelOption) {
                    case HELP:
                        showHelp();
                        break;
                    case PREVIEW_CALC_FILE:
                        previewFile();
                        break;
                    case CALCULATE:
                        calculate();
                        break;
                    case CREATE_EQUATION:
                        createEquation();
                        break;

                    case EXIT:

                    default:
                        voidOperation();
                        break;
                }

                printLogo();
                printMenu();
            }
        }
        catch (NullPointerException e) {
            printMenu();
            panelOption = createFromInt(dataReader.getInt());
        }
        catch (Exception e) {
            voidOperation();
            printMenu();
            panelOption = createFromInt(dataReader.getInt());
        }
    }

    /**
     * Method informing that input number is out of range.
     * Range of supported operations corresponds to the amount of  enums in PanelOption
     */
    private void voidOperation() {
        System.out.println("CalcOperation not supported");
    }

    /**
     * Method creating new equation
     */
    private void createEquation() {
        dataWriter.equationFromScanner();
    }


    /**
     * Method printing to console Calculator's menu
     * Source comes from enum PanelOption
     */
    private void printMenu() {
        for (PanelOption option : PanelOption.values()) {
            System.out.println(option);
        }
    }

    /**
     * Method displaying application's help
     */
    private void showHelp() {
        System.out.println("\n******************");
        System.out.println("Here should be help :-)");
        System.out.println("******************\n");
    }

    /**
     * Covered DataReader method.
     * Displays CALC_FILE content
     */
    private void previewFile() {
        File file = DataFile.getCALC_FILE();
        dataReader.getFilePreview(file);
    }


    /**
     * Method calculation the equation based on CALC_FILE content.
     * It prints to the console the result, file preview and evaluated equation
     */
    private void calculate() {
        //inputs
        File file = DataFile.getCALC_FILE();
        Equation equation = dataReader.getEquation(file);
        calculator.computeEquation(equation);

        //Data file preview
        previewFile();

        //Equation as String
        System.out.println("Following equation has been constructed");
        System.out.println(equation.getStringRepresentation());

        //Result display
        equation.previevResult();
    }
}
