package core;

import command.*;
import core.permission.PermissionLoader;
import listener.MessageListener;
import listener.PermissionListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.managers.GuildController;
import utils.PRIVATE;
import utils.SECRETS;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static JDABuilder builder;
    public static JDA jda = null;
    public static GuildController controller;

    static BufferedReader reader = null;
    public static HashMap<String, String> guild = new HashMap<>();


    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(PRIVATE.BOT_TOKEN);
        builder.setAutoReconnect(true);

        try {
            builder.setGame(Game.of(Game.GameType.DEFAULT, "beeing developed...", "https://www.twitch.tv/thecreeperking56"));
        }catch (NoSuchMethodError e){
            System.out.println("Skipping process of setting Game");
        }

        addCommands();
        addListeners();

        try {
            jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
//        } catch (RateLimitedException e) {
//          e.printStackTrace();
        }

        try {
            PermissionLoader.load();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public static void addCommands(){
        ArrayList<Command> commands = new ArrayList<Command>();

        commands.add(new cmd_Plevel());
        commands.add(new cmd_Help());
        commands.add(new cmd_Bug());
        commands.add(new cmd_Invite());
        commands.add(new cmd_Geh());
        commands.add(new cmd_Random());

        while (!commands.isEmpty()) {
            commandHandler.commands.put(commands.get(commands.size() - 1).name(), commands.get(commands.size() - 1));
            System.out.println("Registrating " + commands.get(commands.size() - 1).name());
            commands.remove(commands.get(commands.size() - 1));
        }


    }

    public static void addListeners() {

        builder.addEventListener(new MessageListener());
        builder.addEventListener(new PermissionListener());

    }

}
