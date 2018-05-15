package core.pi;

import com.pi4j.io.gpio.*;
import utils.SECRETS;

import static core.Main.gpio;

public class PINS {

    public final static GpioPinDigitalOutput ERROR_RED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "MyLED", PinState.LOW);
    public final static GpioPinDigitalOutput ERROR_YELLOW = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "MyLED", PinState.LOW);
    public final static GpioPinDigitalOutput ERROR_GREEN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "MyLED", PinState.LOW);

    public final static GpioPinDigitalOutput CMD_EXECUTING = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "MyLED", PinState.LOW);

    public final static GpioPinDigitalOutput IS_LIVE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyLED", PinState.LOW);
    public final static GpioPinDigitalOutput IN_VOICE_CHANNEL = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLED", PinState.LOW);

    public final static GpioPinDigitalOutput RUNNING = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.LOW);

    public final static GpioPinDigitalOutput FILESYSTEM = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);

    public final static GpioPinDigitalInput TOGGLE_LIVE = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, PinPullResistance.PULL_DOWN);
    public final static GpioPinDigitalInput RECONNECT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_24, PinPullResistance.PULL_DOWN);
    public final static GpioPinDigitalInput RESTART = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);

    public PINS(){
            ERROR_RED.setShutdownOptions(true, PinState.LOW);
            ERROR_YELLOW.setShutdownOptions(true, PinState.LOW);
            ERROR_GREEN.setShutdownOptions(true, PinState.LOW);

            CMD_EXECUTING.setShutdownOptions(true, PinState.LOW);

            IS_LIVE.setShutdownOptions(true, PinState.LOW);
            IN_VOICE_CHANNEL.setShutdownOptions(true, PinState.LOW);

            RUNNING.setShutdownOptions(true, PinState.LOW);

            FILESYSTEM.setShutdownOptions(true, PinState.LOW);

            TOGGLE_LIVE.setShutdownOptions(true, PinState.LOW);
            RECONNECT.setShutdownOptions(true, PinState.LOW);
            RESTART.setShutdownOptions(true, PinState.LOW);
    }

}
