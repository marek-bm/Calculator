package pl.storeware.utils;

import pl.storeware.CalculatorApp;

public class WelcomeLogo {
    public static final String LOGO=
            "###########################################\n" +
            "###           CALCULATOR "+ CalculatorApp.APP_VER +"         ###\n" +
            "###########################################";

    public static void printLogo(){
        System.out.println(LOGO);
    }
}
