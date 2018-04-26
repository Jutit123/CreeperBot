package core.pi;

public class out {

    private static long DURATION_OF_SLEEP = 500;

    public static void error(ErrorType e){
        switch (e){
            case NOT_FOUND:
                PINS.ERROR_RED.high();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case USER_PERMISSION_ERROR:
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.high();
                PINS.ERROR_GREEN.high();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case OVERFLOW_LIMIT:
                PINS.ERROR_RED.high();
                PINS.ERROR_YELLOW.high();
                PINS.ERROR_GREEN.high();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case INVALID_ARGUMENT:
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.high();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case GPIO_ERROR:
                PINS.ERROR_RED.high();
                PINS.ERROR_YELLOW.high();
                PINS.ERROR_GREEN.low();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case CONNECTION_FAILED:
                PINS.ERROR_RED.high();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.high();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;

            case BOT_PERMISSION_ERROR:
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.high();
                PINS.ERROR_GREEN.low();
                try {Thread.sleep(DURATION_OF_SLEEP);} catch (InterruptedException e1) {e1.printStackTrace();}
                PINS.ERROR_RED.low();
                PINS.ERROR_YELLOW.low();
                PINS.ERROR_GREEN.low();
                break;
        }
    }

    public static void execute(boolean is){
        if (is)
            PINS.CMD_EXECUTING.high();
        else
            PINS.CMD_EXECUTING.low();

    }

}
