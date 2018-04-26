package listener;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import core.Main;
import core.pi.PINS;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

import static core.Main.builder;
import static core.Main.jda;
import static core.pi.PINS.*;

public class Info {

    public static void main(String args[]) throws InterruptedException {

        TOGGLE_LIVE.setShutdownOptions(true);

        TOGGLE_LIVE.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                if (event.getPin().equals(TOGGLE_LIVE)) {
                    jda.shutdownNow();
                    RUNNING.low();
                    if (event.getState().isHigh()){
                        try {
                            builder.setGame(Game.of(Game.GameType.STREAMING, "R A K A N", "https://www.twitch.tv/thecreeperking56"));
                        }catch (NoSuchMethodError e){
                            System.out.println("Skipping process of setting Game");
                        }
                        try {
                            jda = builder.buildBlocking();
                        } catch (LoginException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        IS_LIVE.high();
                    }else {
                        try {
                            builder.setGame(Game.of(Game.GameType.DEFAULT, "R A K A N"));
                        }catch (NoSuchMethodError e){
                            System.out.println("Skipping process of setting Game");
                        }
                        try {
                            jda = builder.buildBlocking();
                        } catch (LoginException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        IS_LIVE.high();
                    }
                    RUNNING.high();
                }
            }

        });
    }

}
