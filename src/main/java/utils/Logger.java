package utils;

import command.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.TextColors.*;

public class Logger {

    public static void log(MessageReceivedEvent event, Command cmd){
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy, HH:mm:ss");
        Date date = new Date();

        System.out.format(TEXT_WHITE + "[%s] " + TEXT_YELLOW + " %s " + TEXT_WHITE + "[" + TEXT_PURPLE + "%s" + TEXT_WHITE + "] issued the '" + TEXT_CYAN + "%s" + TEXT_WHITE + "' command!\n",
                dateFormat.format(date), event.getAuthor().getName(), event.getAuthor().getId(), event.getMessage().getContentRaw());
    }

    public static void error(MessageReceivedEvent event){

    }

}
