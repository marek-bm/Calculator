package pl.storeware.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.io.File;

/**
 * Class storing location of default text file CALC_FILE named "calc.txt".
 * It is the most basic solution used as an input for Calculator
 */

@Component
public class DataFile {
    @Getter
    private static File CALC_FILE=new File("calc.txt");
}
