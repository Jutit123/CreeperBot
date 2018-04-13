package command;

import core.Main;
import core.permission.PermissionLevels;
import core.permission.permsCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;

import java.io.IOException;

public class cmd_Geh implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("137542752945700864")) return false;
        return permsCore.checkLvl(PermissionLevels.BOT_OWNER, event);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        Main.jda.shutdownNow();
        System.exit(0);
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
        return "geh";
    }
}
