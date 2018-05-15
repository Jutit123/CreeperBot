package core;

import com.pi4j.io.gpio.*;
import command.*;
import core.permission.PermissionLoader;
import core.pi.ErrorType;
import core.pi.PINS;
import listener.MessageListener;
import listener.PermissionListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.managers.GuildController;
import utils.G_ROLES;
import utils.PRIVATE;
import utils.SECRETS;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static core.pi.PINS.RUNNING;

public class Main {

    public static JDABuilder builder;
    public static JDA jda = null;
    public static GuildController controller;

    static BufferedReader reader = null;
    public static HashMap<String, String> guild = new HashMap<>();

    public static GpioController gpio = null;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, UnsatisfiedLinkError {

        if (args.length == 1) {
            try {
                PRIVATE.setBotToken(Integer.valueOf(args[0]));
            }catch (NumberFormatException e){
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            }catch (IndexOutOfBoundsException e){
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            }catch (NullPointerException e){
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            }
        }else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Args");
            String s = br.readLine();
            PRIVATE.setBotToken(Integer.valueOf(s));
        }

        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            SECRETS.PI = true;
            SECRETS.PATH_TO_DESKTOP_DIRECTORY = "/home/dbot/";
        } else{
            SECRETS.PI = false;
            SECRETS.PATH_TO_DESKTOP_DIRECTORY = System.getProperty("user.home") + "\\Desktop\\XayahBot\\";
        }

        if (SECRETS.PI) {
            gpio = GpioFactory.getInstance();
        }

        if (SECRETS.PI) new PINS();

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
            if (SECRETS.PI) core.pi.out.error(ErrorType.CONNECTION_FAILED);
            e.printStackTrace();
        } catch (InterruptedException e) {
            if (SECRETS.PI) core.pi.out.error(ErrorType.CONNECTION_FAILED);
            e.printStackTrace();
        //} catch (RateLimitedException e) {
        //    e.printStackTrace();
        }

        controller = new GuildController(jda.getGuilds().get(0));

        G_ROLES.defineRoles(controller.getGuild());

        PermissionLoader.load();

        if (SECRETS.PI){
            try {
                new listener.Info();
            } catch (InterruptedException e) {
                core.pi.out.error(ErrorType.OVERFLOW_LIMIT);
                e.printStackTrace();
            }
            RUNNING.high();
        }
    }

    public static void addCommands(){
        ArrayList<Command> commands = new ArrayList<Command>();

        commands.add(new cmd_Plevel());
        commands.add(new cmd_Help());
        commands.add(new cmd_Bug());
        commands.add(new cmd_Invite());
        commands.add(new cmd_Geh());
        commands.add(new cmd_Random());
        commands.add(new cmd_GiveRole());

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
