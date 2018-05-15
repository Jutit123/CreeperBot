package core.pi;

import utils.SECRETS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.TextColors.*;
import static utils.TextColors.TEXT_WHITE;

public class out {

    private static long DURATION_OF_SLEEP = 500;

    public static void error(ErrorType e){
        if (SECRETS.PI) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy, HH:mm:ss");
            Date date = new Date();
            System.out.format(TEXT_WHITE + "[%s] " + TEXT_RED + "%s " + TEXT_WHITE + "occurred\n",
                    dateFormat.format(date), e.name());
            switch (e) {
                case NOT_FOUND:
                    PINS.ERROR_RED.high();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case USER_PERMISSION_ERROR:
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.high();
                    PINS.ERROR_GREEN.high();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case OVERFLOW_LIMIT:
                    PINS.ERROR_RED.high();
                    PINS.ERROR_YELLOW.high();
                    PINS.ERROR_GREEN.high();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case INVALID_ARGUMENT:
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.high();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case GPIO_ERROR:
                    PINS.ERROR_RED.high();
                    PINS.ERROR_YELLOW.high();
                    PINS.ERROR_GREEN.low();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case CONNECTION_FAILED:
                    PINS.ERROR_RED.high();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.high();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;

                case BOT_PERMISSION_ERROR:
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.high();
                    PINS.ERROR_GREEN.low();
                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
                    break;
            }
        }
    }

    public static void execute(boolean is){
        if (SECRETS.PI) {
            if (is)
                PINS.CMD_EXECUTING.high();
            else
                PINS.CMD_EXECUTING.low();
        }
    }

    public static void fileWrite(boolean is){
        if (SECRETS.PI) {
            if (is)
                PINS.FILESYSTEM.high();
            else
                PINS.FILESYSTEM.low();
        }
    }

}
