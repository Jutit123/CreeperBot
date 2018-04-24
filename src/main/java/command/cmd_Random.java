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
        if (args.length < 2) {
            event.getTextChannel().sendMessage("**ERROR**\n" +
                    "Please give at least 2 arguments").queue();
        } else {
            if (args[0].equalsIgnoreCase("-n")) {
                int lowerLimit = 0;
                int upperLimit = 0;
                if (args.length < 3){
                    event.getTextChannel().sendMessage("**ERROR**\n" +
                            "Argument `-n` requires two numbers following it! (From small to big)\n" +
                            "e.g. `" + name() + " -n 1 10`").queue();
                    return;
                }
                try {
                    lowerLimit = Integer.parseInt(args[1]);
                    upperLimit = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    event.getTextChannel().sendMessage("**ERROR**\n" +
                            "Argument `-n` requires two numbers following it! (From small to big)\n" +
                            "e.g. `" + name() + " -n 1 10`").queue();
                    return;
                } catch (IllegalArgumentException f) {
                    event.getTextChannel().sendMessage("**ERROR**\n" +
                            "Argument `-n` requires two numbers following it! (From small to big)\n" +
                            "e.g. `" + name() + " -n 1 10`").queue();
                    return;
                }
                event.getTextChannel().sendMessage("Random result: **" + (lowerLimit + new Random().nextInt((upperLimit - lowerLimit) + 1)) + "**").queue();
            } else {
                String[] command = event.getMessage().getContentRaw().replaceFirst(SECRETS.PREFIX + name() + " ", "").split(", ");
                event.getTextChannel().sendMessage("Random result: **" + command[new Random().nextInt(command.length - 1)] + "**").queue();
            }
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
