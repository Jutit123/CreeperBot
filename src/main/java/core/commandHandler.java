package core;

import command.Command;
import core.pi.ErrorType;
import net.dv8tion.jda.core.EmbedBuilder;
import utils.SECRETS;

import java.io.IOException;
import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public static void handleCommand(commandParser.commandContainer cmd) {

        if (commands.containsKey(cmd.invoke.toLowerCase())) {
            core.pi.out.execute(true);
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                try {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                }catch (net.dv8tion.jda.core.exceptions.InsufficientPermissionException e){
                    core.pi.out.error(ErrorType.BOT_PERMISSION_ERROR);
                    cmd.event.getTextChannel().sendMessage(new EmbedBuilder()
                            .setColor(SECRETS.RAKAN_PURPLE)
                            .setTitle("INSUFFICIENT PERMISSIONS")
                            .setDescription("Bot is missing the \"" + e.getPermission().getName() +
                                    "\" permission!\nCan't act as intended.").build()).queue();
                }catch (IOException e){
                    e.printStackTrace();
                }
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
            core.pi.out.execute(false);
        }

    }

}