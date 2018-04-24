package command;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import core.Main;

import static core.Main.gpio;

public class Info {

        public Info(){

            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_DOWN);

            myButton.setShutdownOptions(true, PinState.LOW);

            myButton.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                    if (event.getPin().equals(myButton)) {
                        if (event.getState().isHigh()){
                            Main.controller.getGuild().getDefaultChannel().sendMessage("Bot will be buggy for a while, due to debug! :frowning:").queue();
                        }
                    }

                }

            });

        }

}
