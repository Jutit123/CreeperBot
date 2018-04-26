package core.permission;

import core.pi.ErrorType;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.SECRETS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class permsCore {

    public static HashMap<User, Byte> permLvl;

    public static boolean checkLvl(PermissionLevels lvlRequired, MessageReceivedEvent event) {
        Member m = event.getMember();
        for (PermissionLoader.UserPermission up : PermissionLoader.permissions){
            if (up.getUserID().equals(event.getAuthor().getId())) {
                if (up.getGuildID().equals(event.getGuild().getId())) {
                    if (lvlRequired.ordinal() <= up.getPermissionLevel()){
                        return false;
                    }
                }
            }
        }
        if (event.getMember().getUser().getId().equals("137542752945700864")) {
            /**
             Message msg1 = event.getTextChannel().sendMessage(new EmbedBuilder()
             .setTitle("Ooups!")
             .setDescription("Found TheCreeperKing56 granting permission due to " +
             "debug purposes.")
             .setColor(Color.GREEN).build()).complete();

             new Timer().schedule(new TimerTask() {
            @Override public void run() {
            msg1.delete().queue();
            }
            }, SECRETS.notAllowedTimeout);
             */
            //return false;
        }
        Message msg = event.getTextChannel().sendMessage(":warning:  Sorry, "
                + event.getAuthor().getAsMention()
                + ", you don't have the permissions to use this command! (Permission Level `[" + lvlRequired.ordinal() +
                "] - " + lvlRequired + "` is required)").complete();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                event.getMessage().delete().queue();
                msg.delete().queue();
            }
        }, SECRETS.NOT_ALLOWED_TIMOUT);
        core.pi.out.error(ErrorType.USER_PERMISSION_ERROR);
        return true;
    }

    public static boolean checkRole(String[] roleGroup, MessageReceivedEvent event) {

        for (Role r : event.getGuild().getMember(event.getAuthor()).getRoles()) {
            if (Arrays.stream(roleGroup).parallel().anyMatch(r.getName()::contains))
                return false;
        }
        if (event.getMember().getUser().getId().equals("137542752945700864")) {
            /**
             Message msg1 = event.getTextChannel().sendMessage(new EmbedBuilder()
             .setTitle("Ooups!")
             .setDescription("Found TheCreeperKing56 granting permission due to " +
             "debug purposes.")
             .setColor(Color.GREEN).build()).complete();

             new Timer().schedule(new TimerTask() {
            @Override public void run() {
            msg1.delete().queue();
            }
            }, SECRETS.notAllowedTimeout);
             */
            return false;
        }
        Message msg = event.getTextChannel().sendMessage(":warning:  Sorry, "
                + event.getAuthor().getAsMention()
                + ", you don't have the permissions to use this command!").complete();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                event.getMessage().delete().queue();
                msg.delete().queue();
            }
        }, SECRETS.NOT_ALLOWED_TIMOUT);
        core.pi.out.error(ErrorType.USER_PERMISSION_ERROR);
        return true;
    }

    public static boolean checkUser(String[] nameGroup, MessageReceivedEvent event) {
        Member m = event.getMember();
        if (Arrays.stream(nameGroup).parallel().anyMatch(m.getUser().getId()::contains))
            return false;
        if (event.getMember().getUser().getId().equals("137542752945700864")) {
            /**
             Message msg1 = event.getTextChannel().sendMessage(new EmbedBuilder()
             .setTitle("Ooups!")
             .setDescription("Found TheCreeperKing56 granting permission due to " +
             "debug purposes.")
             .setColor(Color.GREEN).build()).complete();

             new Timer().schedule(new TimerTask() {
            @Override public void run() {
            msg1.delete().queue();
            }
            }, SECRETS.notAllowedTimeout);
             */
            return false;
        }
        Message msg = event.getTextChannel().sendMessage(":warning:  Sorry, "
                + event.getAuthor().getAsMention()
                + ", you don't have the permissions to use this command!").complete();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                event.getMessage().delete().queue();
                msg.delete().queue();
            }
        }, SECRETS.NOT_ALLOWED_TIMOUT);
        core.pi.out.error(ErrorType.USER_PERMISSION_ERROR);
        return true;
    }

}