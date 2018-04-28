package listener;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import core.Main;
import core.pi.ErrorType;
import core.pi.PINS;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

import java.io.IOException;

import static core.Main.builder;
import static core.Main.jda;
import static core.pi.PINS.*;

public class Info {

    boolean live = false;

    public Info() throws InterruptedException {

        TOGGLE_LIVE.setShutdownOptions(true);
        RESTART.setShutdownOptions(true);


        TOGGLE_LIVE.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                if (event.getPin().equals(TOGGLE_LIVE)) {
                    if (event.getState().isHigh()) live = !live;
                    jda.shutdownNow();
                    RUNNING.low();
                    if (live){
                        try {
                            builder.setGame(Game.of(Game.GameType.STREAMING, "R A K A N", "https://www.twitch.tv/thecreeperking56"));
                        }catch (NoSuchMethodError e){
                            System.out.println("Skipping process of setting Game");
                        }
                        IS_LIVE.high();
                    }else {
                        try {
                            builder.setGame(Game.of(Game.GameType.DEFAULT, "R A K A N"));
                        }catch (NoSuchMethodError e){
                            System.out.println("Skipping process of setting Game");
                        }
                        IS_LIVE.low();
                    }
                    try {
                        jda = builder.buildBlocking();
                    } catch (LoginException e) {
                        e.printStackTrace();
                        core.pi.out.error(ErrorType.CONNECTION_FAILED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        core.pi.out.error(ErrorType.CONNECTION_FAILED);
                    }
                    RUNNING.high();
                }
            }

        });

        RESTART.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                if (event.getPin().equals(RESTART)) {
                    if(event.getState().equals(PinState.HIGH)){
                        try {
                            Process proc = Runtime.getRuntime().exec("sudo reboot -n");
                            proc.waitFor();
                        } catch (IOException e) {
                            e.printStackTrace();
                            core.pi.out.error(ErrorType.NOT_FOUND);
                        } catch (InterruptedException e) {
                            core.pi.out.error(ErrorType.OVERFLOW_LIMIT);
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                }
            }

        });
    }

}
