//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package command;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;

public class cmd_lategame implements Command {
    public cmd_lategame() {
    }

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        String hh = "0";
        String mm = "0";
        String[] champ = new String[]{"Talon", "Vladimir", "Fiora", "Lux", "Renekton", "Darius"};
        if (calendar.getTime().getHours() < 10) {
            hh = hh + String.valueOf(calendar.getTime().getHours());
        } else {
            hh = String.valueOf(calendar.getTime().getHours());
        }

        if (calendar.getTime().getMinutes() < 10) {
            mm = mm + String.valueOf(calendar.getTime().getMinutes());
        } else {
            mm = String.valueOf(calendar.getTime().getMinutes());
        }

        event.getMessage().getTextChannel().sendMessage("```\n[" + hh + ":" + mm + "] [All] Zamasu was right (" + champ[random.nextInt(champ.length)] + "): LATEGAME!\n```").queue();
    }

    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.log(event, this);
    }

    public String help(MessageReceivedEvent event) {
        return null;
    }

    public boolean listedByHelp() {
        return false;
    }

    public boolean imAway() {
        return false;
    }

    public String name() {
        return "lategame";
    }
}
