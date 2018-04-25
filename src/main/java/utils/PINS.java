package utils;

import com.pi4j.io.gpio.*;

import static core.Main.gpio;

public class PINS {

    public static final GpioPinDigitalInput stinky = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_DOWN);
    public static final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_DOWN);
    public static final GpioPinDigitalInput nö = gpio.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);
    public static final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "MyLED", PinState.LOW);

}
