package command;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import core.Main;
import net.dv8tion.jda.core.entities.Game;
import utils.PINS;

import javax.security.auth.login.LoginException;

import static core.Main.builder;
import static core.Main.gpio;
import static core.Main.jda;

public class Info {

        public Info(){


            PINS.myButton.setShutdownOptions(true, PinState.LOW);
            PINS.stinky.setShutdownOptions(true, PinState.LOW);
            PINS.nö.setShutdownOptions(true, PinState.LOW);

            PINS.nö.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                    if (event.getPin().equals(PINS.nö)) {
                        PINS.pin.high();
                        jda.shutdownNow();
                        if (event.getState().isHigh()){
                            try {
                                Main.builder.setGame(Game.of(Game.GameType.STREAMING, "R A K A N", "https://www.twitch.tv/thecreeperking56"));
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
                        }
                        if (event.getState().isLow()){
                            try {
                                Main.builder.setGame(Game.of(Game.GameType.DEFAULT, "R A K A N"));
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
                        }
                        PINS.pin.low();
                    }

                }

            });
            PINS.stinky.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                    if (event.getPin().equals(PINS.stinky)){
                        if (event.getState().isHigh()){
                            Main.controller.getGuild().getMemberById("137542752945700864").getUser().openPrivateChannel().queue(privateChannel ->
                            {
                                privateChannel.sendMessage("KONRAD STINKT! :shit:").queue();
                            });
                        }
                    }

                }

            });
            PINS.myButton.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                    if (event.getPin().equals(PINS.myButton)) {
                        if (event.getState().isHigh()){
                            Main.controller.getGuild().getTextChannelById("360052444908093442").sendMessage("Bot will be buggy for a while, due to debug! :frowning:").queue();
                        }
                    }

                }

            });
        }

}
