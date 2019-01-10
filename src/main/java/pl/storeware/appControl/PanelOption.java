package pl.storeware.appControl;


/**
 * PanelOption collects enum objects supporting functionality user interface of the application
 */
public enum PanelOption {

    EXIT(0, "Exit"),

    HELP(1, "Help"),

    PREVIEW_CALC_FILE(2, "Preview calc-sheet"),

    CALCULATE (3, "Calculate current equation"),

    CREATE_EQUATION (4, "New equation");

    private int value;

    private String description;

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    PanelOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + "-" + description;
    }

    public static PanelOption createFromInt(int option) {
        return values()[option];
    }
}
