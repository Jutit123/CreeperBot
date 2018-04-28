package command;

import core.pi.ErrorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;
import utils.SECRETS;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class cmd_Help implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        if (args.length == 0) {
            if (SECRETS.PI) core.pi.out.error(ErrorType.INVALID_ARGUMENT);
            Message msg = event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setTitle("**ERROR**")
                    .setDescription("Please specify a which command should be displayed! (e.g. `help`)").build()).complete();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    event.getMessage().delete().queue();
                    msg.delete().queue();
                }
            }, 10 * 1000);
            return;
        }
        switch (args[0].toLowerCase()){
            case "help":
                event.getTextChannel().sendMessage("**HELP for `" + args[0] + "`**\n" +
                        "Help is a command just for displaying syntax and more for some commands\n\n" +
                        "Available help menus exist for following tags:\n" +
                        "`help`, `plevel`, `invite`, `bug`, `random`").queue();
                break;
            case "random":
                event.getTextChannel().sendMessage("**HELP for `" + args[0] + "`**\n" +
                        "Random is a command for select one option for you\n\n" +
                        "Available tags exist:\n" +
                        "`-n`").queue();
                break;
            case "bug":
            case "invite":
                event.getTextChannel().sendMessage("**HELP for `" + args[0] + "`**\n" +
                        "Just run it :P").queue();
                break;
            case "plevel":
                if (args.length == 1){
                    event.getTextChannel().sendMessage("**HELP for `" + args[0] + "`**\n" +
                            "plevel is a command use to change the permission of someone\n\n" +
                            "Available tags exist **after mention of the user you want to edit**:\n" +
                            "`lvl`, `get`").queue();
                    return;
                }
                switch (args[1].toLowerCase()){
                    case "lvl":
                        event.getTextChannel().sendMessage("**HELP for `" + args[0] + " " + args[1] + "`**\n" +
                                "level is used to set the permission level of *user*\n\n" +
                                "_plevel <@mention> (level as number from 0 - 6)_").queue();
                        break;
                    case "get":
                        event.getTextChannel().sendMessage("**HELP for `" + args[0] + " " + args[1] + "`**\n" +
                                "get is used to display the permission level of *user*\n\n" +
                                "_plevel <@mention> get_").queue();
                        break;
                    default:
                        if (SECRETS.PI) core.pi.out.error(ErrorType.INVALID_ARGUMENT);
                        event.getTextChannel().sendMessage("*ERROR**\n" +
                                "Please enter a valid tag!").queue();
                        break;
                }
                break;
            default:
                Message msg = event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("**ERROR**")
                        .setDescription("Please specify a valid command which should be displayed! (e.g. `help`)").build()).complete();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        event.getMessage().delete().queue();
                        msg.delete().queue();
                    }
                }, 10 * 1000);
                break;
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
        return "help";
    }
}
