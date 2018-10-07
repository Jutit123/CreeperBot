package core.pi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.SECRETS;

public class out {
    private static long DURATION_OF_SLEEP = 500L;

    public out() {
    }

    public static void startup(int repeats) throws InterruptedException {
        for(int i = 0; i < repeats; ++i) {
            startup();
        }

    }

    public static void startup() throws InterruptedException {
        PINS.RUNNING.high();
        Thread.sleep(500L);
        PINS.RUNNING.low();
        Thread.sleep(100L);
        PINS.FILESYSTEM.high();
        Thread.sleep(500L);
        PINS.FILESYSTEM.low();
        Thread.sleep(100L);
        PINS.IS_LIVE.high();
        Thread.sleep(500L);
        PINS.IS_LIVE.low();
        Thread.sleep(100L);
        PINS.ERROR_RED.high();
        Thread.sleep(500L);
        PINS.ERROR_RED.low();
        Thread.sleep(100L);
        PINS.ERROR_GREEN.high();
        Thread.sleep(500L);
        PINS.ERROR_GREEN.low();
        Thread.sleep(100L);
        PINS.ERROR_YELLOW.high();
        Thread.sleep(500L);
        PINS.ERROR_YELLOW.low();
        Thread.sleep(100L);
        PINS.CMD_EXECUTING.high();
        Thread.sleep(500L);
        PINS.CMD_EXECUTING.low();
        Thread.sleep(100L);
        PINS.IN_VOICE_CHANNEL.high();
        Thread.sleep(500L);
        PINS.IN_VOICE_CHANNEL.low();
        Thread.sleep(100L);
    }

    public static void error(ErrorType e) {
        if (SECRETS.PI) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy, HH:mm:ss");
            Date date = new Date();
            System.out.format("\u001b[37m[%s] \u001b[31m%s \u001b[37moccurred\n", dateFormat.format(date), e.name());
            switch(e) {
                case NOT_FOUND:
                    PINS.ERROR_RED.high();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();

                    try {
                        Thread.sleep(DURATION_OF_SLEEP);
                    } catch (InterruptedException var10) {
                        var10.printStackTrace();
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
                    } catch (InterruptedException var9) {
                        var9.printStackTrace();
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
                    } catch (InterruptedException var8) {
                        var8.printStackTrace();
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
                    } catch (InterruptedException var7) {
                        var7.printStackTrace();
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
                    } catch (InterruptedException var6) {
                        var6.printStackTrace();
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
                    } catch (InterruptedException var5) {
                        var5.printStackTrace();
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
                    } catch (InterruptedException var4) {
                        var4.printStackTrace();
                    }

                    PINS.ERROR_RED.low();
                    PINS.ERROR_YELLOW.low();
                    PINS.ERROR_GREEN.low();
            }
        }

    }

    public static void execute(boolean is) {
        if (SECRETS.PI) {
            if (is) {
                PINS.CMD_EXECUTING.high();
            } else {
                PINS.CMD_EXECUTING.low();
            }
        }

    }

    public static void fileWrite(boolean is) {
        if (SECRETS.PI) {
            if (is) {
                PINS.FILESYSTEM.high();
            } else {
                PINS.FILESYSTEM.low();
            }
        }

    }

    public static void isLive(boolean is) {
        if (SECRETS.PI) {
            if (is) {
                PINS.IS_LIVE.high();
            } else {
                PINS.IS_LIVE.low();
            }
        }

    }
}
