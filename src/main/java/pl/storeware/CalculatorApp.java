package pl.storeware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.storeware.appControl.ControlPanel;

/**
 * Class launching Calculator
 */
public class CalculatorApp {
    public static final String APP_VER = "v 0.1 ";


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ControlPanel panel = context.getBean(ControlPanel.class);

        panel.runControlLoop();
    }
}
