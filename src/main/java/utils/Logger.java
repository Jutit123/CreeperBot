package utils;

import command.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static utils.TextColors.*;

public class Logger {

    public static void log(MessageReceivedEvent event, Command cmd){
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy, HH:mm:ss");
        Date date = new Date();

        String log = TEXT_WHITE + "[" + dateFormat.format(date) + "] " + TEXT_YELLOW + " " + event.getAuthor().getName() + " " +
                TEXT_WHITE + "[" + TEXT_PURPLE + event.getAuthor().getId() + TEXT_WHITE + "] issued the '" + TEXT_CYAN + event.getMessage().getContentRaw() + TEXT_WHITE + "' command!\n";

        System.out.print(log);
        logs.add(log);
        if (logs.size() >= 20){
            if (SECRETS.PI) core.pi.out.fileWrite(true);
            writeLog(logs);
            logs.clear();
            if (SECRETS.PI) core.pi.out.fileWrite(false);
        }
    }

    public static void error(MessageReceivedEvent event){

    }

    private static void writeLog(ArrayList<String> log) {
        try {
            System.out.println(TextColors.clearString(log.get(0)).substring(1, 22).replace(":", " -") +
                    "_to_" + TextColors.clearString(log.get(log.size() - 1)).substring(1, 22).replace(":", " -") + "_log.txt");
            File file = new File(SECRETS.PATH_TO_DESKTOP_DIRECTORY + "logs/" + TextColors.clearString(log.get(0)).substring(1, 22).replace(":", " -") +
                    "_to_" + TextColors.clearString(log.get(log.size() - 1)).substring(1, 22).replace(":", " -") + "_log.txt");
            if (!file.exists()) file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String s : log){
                writer.write(TextColors.clearString(s) + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Logged Latest " + TextColors.TEXT_CYAN + log.size() + TextColors.TEXT_WHITE + " commands!");
            log.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> logs = new ArrayList<>();

}
