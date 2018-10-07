package command;

import core.Main;
import core.permission.PermissionLevels;
import core.permission.permsCore;
import core.pi.ErrorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;
import utils.PRIVATE;
import utils.SECRETS;

import java.io.IOException;

public class cmd_Force implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("137542752945700864")) return false;
        return permsCore.checkLvl(PermissionLevels.CO_OWNER, event);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        if (args.length == 0) {
            core.pi.out.error(ErrorType.INVALID_ARGUMENT);
            event.getTextChannel().sendMessage(":warning: Invalid arguments.").queue();
            return;
        }
        switch (args[0]){
            case "live":
                if (args.length == 1) {
                    core.pi.out.error(ErrorType.INVALID_ARGUMENT);
                    event.getTextChannel().sendMessage(":warning: Invalid arguments.").queue();
                    return;
                }
                switch (args [1]){
                    default:
                        event.getTextChannel().sendMessage(":warning: Not enough arguments `" + SECRETS.PREFIX + "help`").queue();
                        break;
                    case "winzi":
                        try {
                            event.getTextChannel().sendMessage((new EmbedBuilder())
                                    .setTitle("NEUER STREAM VON " + Main.controller.getGuild().getMemberById(PRIVATE.WINZI_ID).getEffectiveName())
                                    .setColor(SECRETS.RAKAN_PURPLE)
                                    .setThumbnail(Main.controller.getGuild().getMemberById(PRIVATE.WINZI_ID).getUser().getEffectiveAvatarUrl())
                                    .setDescription(Main.controller.getGuild().getMemberById(PRIVATE.WINZI_ID).getGame().getName() + "\nSchau vorbei: " + Main.controller.getGuild().getMemberById(PRIVATE.WINZI_ID).getGame().getUrl())
                                    .build()).queue();
                        }catch (NullPointerException e){ System.out.println("No default Channel found.");}
                        break;
                }
                break;
            default:
                event.getChannel().sendMessage(":warning: Invalid arguments. `" + SECRETS.PREFIX + "help`").queue();
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
        return "force";
    }
}
