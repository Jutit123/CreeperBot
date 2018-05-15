package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;
import utils.SECRETS;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static util.utils.getInt;

public class cmd_Purge implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        int numb = getInt(args[0]);
        if(numb > 1 && numb <= 100){
            try {
                event.getMessage().delete().queue();
                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> message;
                message = history.retrievePast(numb).complete();
                event.getTextChannel().deleteMessages(message).queue();
                Message msg = event.getMessage().getTextChannel().sendMessage(new EmbedBuilder()
                        .setDescription("Successfully purged the last " + numb + " messages!")
                        .setColor(Color.CYAN)
                        .setTitle("***BULK DELETE***").build()).complete();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        msg.delete().queue();
                    }
                }, 5000);
            }catch (Exception er){
                er.printStackTrace();
            }
        }else {
            event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setColor(SECRETS.XAYAH_RED)
                    .setDescription("Number over 1, lower than 99!")
                    .setTitle("***ERROR***").build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.log(event, this);
    }

    @Override
    public String help(MessageReceivedEvent event) {
        return null;
    }

    @Override
    public boolean listedByHelp() {
        return true;
    }

    @Override
    public boolean imAway() {
        return true;
    }

    @Override
    public String name() {
        return "purge";
    }
}
