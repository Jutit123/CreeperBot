package command;

import core.Main;
import core.permission.PermissionLevels;
import core.permission.permsCore;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sun.java2d.windows.GDIRenderer;
import utils.G_ROLES;
import utils.Logger;

import java.io.IOException;

import static core.Main.controller;

public class cmd_GiveRole implements Command{


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return permsCore.checkLvl(PermissionLevels.USER, event);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        Member m;
        Role r;
        if (args.length == 0){
            String s = "";
            for (String d : G_ROLES.G_ROLES){
                s += d + ",\n";
            }
            s = s.substring(0, s.length() - 2);
            event.getTextChannel().sendMessage("All available \"Groles\":\n ```\n" + s + "\n```").queue();
        }else {
            switch (args[0].toLowerCase()) {
                default:
                    event.getTextChannel().sendMessage("Please select: **add** or **remove**").queue();
                    break;
                case "add":
                    if (args.length == 2)
                        m = event.getMember();
                    else
                        m = event.getMessage().getMentionedMembers().get(0);
                    for (String s : G_ROLES.G_ROLES) {
                        if (args[1].replaceAll("_", " ").equalsIgnoreCase(s.replaceAll("_", " "))) {
                            r = controller.getGuild().getRolesByName(args[1].replaceAll("_", " "), true).get(0);
                            controller.addSingleRoleToMember(m, r).queue();
                            event.getTextChannel().sendMessage("Added role **" + args[1].replaceAll("_", " ") + "** to " + m.getAsMention()).queue();
                            return;
                        }
                    }
                    event.getTextChannel().sendMessage("Role **" + args[1].replaceAll("_", " ") + "** not found.").queue();
                    break;
                case "remove":
                    if (args.length == 2)
                        m = event.getMember();
                    else
                        m = event.getMessage().getMentionedMembers().get(0);
                    for (String s : G_ROLES.G_ROLES) {
                        if (args[1].replaceAll("_", " ").equalsIgnoreCase(s.replaceAll("_", " "))) {
                            r = controller.getGuild().getRolesByName(args[1].replaceAll("_", " "), true).get(0);
                            controller.removeSingleRoleFromMember(m, r).queue();
                            event.getTextChannel().sendMessage("Removed role **" + args[1].replaceAll("_", " ") + "** from " + m.getAsMention()).queue();
                            return;
                        }
                    }
                    event.getTextChannel().sendMessage("Role **" + args[1].replaceAll("_", " ") + "** not found.").queue();
                    break;
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
        return "grole";
    }
}
