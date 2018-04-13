package command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;
import utils.SECRETS;

import java.io.IOException;
import java.util.Random;

public class cmd_Random implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        String[] command = event.getMessage().getContentRaw().replaceFirst(SECRETS.PREFIX + name() + " ", "").split(", ");
        event.getTextChannel().sendMessage("Random result: **" + command[new Random().nextInt(command.length - 1)] + "**").queue();
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
        return false;
    }

    @Override
    public boolean imAway() {
        return false;
    }

    @Override
    public String name() {
        return "random";
    }
}
