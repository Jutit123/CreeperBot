package command;

import core.permission.PermissionLevels;
import core.permission.PermissionLoader;
import core.permission.permsCore;
import core.pi.ErrorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;
import utils.SECRETS;

import java.io.IOException;

public class cmd_Plevel implements Command{

    private static boolean permAdd = false;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("c!plevel fill") && event.getAuthor().getId().equals("137542752945700864")) {
            try {
                PermissionLoader.fillGuild(event.getGuild());
                PermissionLoader.reload();
                event.getTextChannel().sendMessage("Added Guild to permissions").queue();
            } catch (IOException e) { e.printStackTrace(); }
            permAdd = true;
            return false;
        }else if (event.getMessage().getContentRaw().equals("c!plevel reload") && event.getAuthor().getId().equals("137542752945700864")) {
            try {
                PermissionLoader.reload();
            } catch (IOException e) { e.printStackTrace(); }
            permAdd = true;
            return false;
        }
        if (event.getAuthor().getId().equals("137542752945700864")) return false;
        return permsCore.checkLvl(PermissionLevels.CO_OWNER, event);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        //SYNTAX: pLevel <@Mention> <lvl/get>
        if (permAdd) {
            permAdd = false;
            return;
        }
        if (args.length == 0) {
            core.pi.out.error(ErrorType.INVALID_ARGUMENT);
            event.getTextChannel().sendMessage("**`" + name() + "` - HELP**\n\n" +
                    "`" + name() + "` is the permission system for this bot!\n" +
                    "You can use the following arguements after a **MentionedUser** with this command:\n" +
                    "  \u2BC0  `get`   -   returns the permission level of the specified user\n" +
                    "  \u2BC0  `<number>`   -   to set the permission level of the specified user (0-6)\n").queue();
            return;
        }
        try {
            Byte.parseByte(args[1]);
        }catch (NumberFormatException e) {
            System.out.println(0);
            if (args[1].equalsIgnoreCase("get")) {
                System.out.println(1);
                for (PermissionLoader.UserPermission ud : PermissionLoader.permissions) {
                    System.out.println(2);
                    if (ud.getUserID().equals(event.getMessage().getMentionedUsers().get(0).getId())) {
                        System.out.println(3);
                        if (ud.getGuildID().equals(event.getGuild().getId())) {
                            System.out.println(4);
                            event.getTextChannel().sendMessage(":information_source: **`" + event.getMessage().getMentionedUsers().get(0).getName() +
                                    "`** has a permission level of: `[" +
                                    ud.getPermissionLevel() + "] - " + PermissionLevels.values()[ud.getPermissionLevel()] + "`").queue();
                        }
                    }
                }
            }
            return;
        }catch (ArrayIndexOutOfBoundsException e){
            core.pi.out.error(ErrorType.INVALID_ARGUMENT);
            event.getTextChannel().sendMessage("**SYNTAX ERROR**\n" +
                    "Coorect syntax of _" + name() + "_ would be `" + SECRETS.PREFIX + name() + " <@Mention> <lvl/get>`").queue();
            return;
        }

        PermissionLoader.UserPermission otherPermLvl = PermissionLoader.getPerm(event.getAuthor(), event.getGuild());
        PermissionLoader.UserPermission permLvl = PermissionLoader.getPerm(event.getMessage().getMentionedUsers().get(0), event.getGuild());

        if (Byte.valueOf(args[1]) < otherPermLvl.getPermissionLevel() && permLvl.getPermissionLevel() < otherPermLvl.getPermissionLevel()) {
            PermissionLoader.permissions.remove(permLvl);
            PermissionLoader.permissions.add(new PermissionLoader.UserPermission(permLvl.getUserID(), permLvl.getGuildID(), Byte.valueOf(args[1])));
            event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setTitle("SUCCESS")
                    .setDescription(event.getAuthor().getAsMention() + " :white_check_mark: Successfully set the permission level of " +
                            event.getMessage().getMentionedMembers().get(0).getAsMention() + " to **" + args[1] + "**").build()).queue();
        }else {
            core.pi.out.error(ErrorType.INVALID_ARGUMENT);
            event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setTitle("ERROR")
                    .setDescription(event.getAuthor().getAsMention() + " :warning: You cant set the permission level of " +
                            event.getMessage().getMentionedMembers().get(0).getAsMention() + " to something higher or equal to your level!\n" +
                            "Or " + event.getMessage().getMentionedMembers().get(0).getAsMention() + " has a permission level equal or higher than yours").build()).queue();
        }
        try {
            PermissionLoader.toFile();
            PermissionLoader.reload();
        } catch (IOException e) { e.printStackTrace(); }

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
        return false;
    }

    @Override
    public String name() {
        return "plevel";
    }
}
