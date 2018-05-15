package utils;

public class TextColors {

    public static final String      RESET                    = "\u001B[0m";

    public static final String TEXT_BLACK                    = "\u001B[30m";
    public static final String TEXT_RED                      = "\u001B[31m";
    public static final String TEXT_GREEN                    = "\u001B[32m";
    public static final String TEXT_YELLOW                   = "\u001B[33m";
    public static final String TEXT_BLUE                     = "\u001B[34m";
    public static final String TEXT_PURPLE                   = "\u001B[35m";
    public static final String TEXT_CYAN                     = "\u001B[36m";
    public static final String TEXT_WHITE                    = "\u001B[37m";

    public static final String BACK_BLACK                    = "\u001B[40m";
    public static final String BACK_RED                      = "\u001B[41m";
    public static final String BACK_GREEN                    = "\u001B[42m";
    public static final String BACK_YELLOW                   = "\u001B[43m";
    public static final String BACK_BLUE                     = "\u001B[44m";
    public static final String BACK_PURPLE                   = "\u001B[45m";
    public static final String BACK_CYAN                     = "\u001B[46m";
    public static final String BACK_WHITE                    = "\u001B[47m";

    public static String clearString(String string){
        return string
                .replace(String.valueOf(RESET), "")

                .replace(String.valueOf(TEXT_BLACK), "")
                .replace(String.valueOf(TEXT_RED), "")
                .replace(String.valueOf(TEXT_GREEN), "")
                .replace(String.valueOf(TEXT_YELLOW), "")
                .replace(String.valueOf(TEXT_BLUE), "")
                .replace(String.valueOf(TEXT_PURPLE), "")
                .replace(String.valueOf(TEXT_CYAN), "")
                .replace(String.valueOf(TEXT_WHITE), "")

                .replace(String.valueOf(BACK_BLACK), "")
                .replace(String.valueOf(BACK_RED), "")
                .replace(String.valueOf(BACK_GREEN), "")
                .replace(String.valueOf(BACK_YELLOW), "")
                .replace(String.valueOf(BACK_BLUE), "")
                .replace(String.valueOf(BACK_PURPLE), "")
                .replace(String.valueOf(BACK_CYAN), "")
                .replace(String.valueOf(BACK_WHITE), "");
    }

}
