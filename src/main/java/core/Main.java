//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import command.Command;
import command.cmd_Bug;
import command.cmd_Geh;
import command.cmd_Help;
import command.cmd_Invite;
import command.cmd_Plevel;
import command.cmd_Random;
import command.cmd_lategame;
import core.permission.PermissionLoader;
import core.pi.ErrorType;
import core.pi.PINS;
import core.pi.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.security.auth.login.LoginException;

import listener.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.managers.GuildController;
import utils.PRIVATE;
import utils.SECRETS;

public class Main {
    public static JDABuilder builder;
    public static JDA jda = null;
    public static GuildController controller;
    static BufferedReader reader = null;
    public static HashMap<String, String> guild = new HashMap();
    public static GpioController gpio = null;

    public Main() {
    }

    public static void main(String[] args) throws IOException, UnsatisfiedLinkError {
        if (args.length == 1) {
            try {
                PRIVATE.setBotToken(Integer.valueOf(args[0]));
            } catch (NumberFormatException var7) {
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            } catch (IndexOutOfBoundsException var8) {
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            } catch (NullPointerException var9) {
                System.err.println(args[0] + " is not an integer to describe bot destinaton");
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Args");
            System.out.println("0 ... DevelopBot");
            System.out.println("1 ... RakanBot");
            System.out.println("2 ... KaliiZzta's Freund");
            System.out.println("3 ... XayahBot");
            System.out.println("4 ... TestingBot");
            System.out.print("I want to select: ");
            String s = br.readLine();
            PRIVATE.setBotToken(Integer.valueOf(s));
            System.out.println("--------------------------------------");
        }

        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            SECRETS.PI = true;
            SECRETS.PATH_TO_DESKTOP_DIRECTORY = "/home/dbot/";
        } else {
            SECRETS.PI = false;
            SECRETS.PATH_TO_DESKTOP_DIRECTORY = System.getProperty("user.home") + "\\Desktop\\XayahBot\\";
        }

        if (SECRETS.PI) {
            gpio = GpioFactory.getInstance();
        }

        if (SECRETS.PI) {
            new PINS();
        }

        if (SECRETS.PI) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        out.startup();
                    } catch (InterruptedException var2) {
                        var2.printStackTrace();
                    }

                }
            });
        }

        builder = new JDABuilder(AccountType.BOT);
        builder.setToken(PRIVATE.BOT_TOKEN);
        builder.setAutoReconnect(true);
        String statusText = "beeing developed...";
        if (args.length == 2) {
            statusText = args[1];
        }

        GameType gameType = GameType.DEFAULT;
        if (args.length == 3) {
            gameType = GameType.values()[Integer.valueOf(args[2])];
        }

        String linkToString = "https://www.twitch.tv/thecreeperking56";
        if (args.length == 4) {
            linkToString = args[3];
        }

        try {
            builder.setGame(Game.of(gameType, statusText, linkToString));
        } catch (NoSuchMethodError var6) {
            System.out.println("Skipping process of setting Game");
        }

        addCommands();
        addListeners();

        try {
            jda = builder.buildBlocking();
        } catch (LoginException var10) {
            if (SECRETS.PI) {
                out.error(ErrorType.CONNECTION_FAILED);
            }

            var10.printStackTrace();
        } catch (InterruptedException var11) {
            if (SECRETS.PI) {
                out.error(ErrorType.CONNECTION_FAILED);
            }

            var11.printStackTrace();
        }

        controller = new GuildController((Guild)jda.getGuilds().get(0));
        PermissionLoader.load();
        if (SECRETS.PI) {
            try {
                new Info();
            } catch (InterruptedException var5) {
                out.error(ErrorType.OVERFLOW_LIMIT);
                var5.printStackTrace();
            }

            PINS.RUNNING.high();
            if (((Guild)jda.getGuilds().get(0)).getMemberById("207217596653764608").getGame().getType().equals(GameType.STREAMING)) {
                out.isLive(true);
            }
        }

    }

    public static void addCommands() {
        ArrayList<Command> commands = new ArrayList();
        commands.add(new cmd_Plevel());
        commands.add(new cmd_Help());
        commands.add(new cmd_Bug());
        commands.add(new cmd_Invite());
        commands.add(new cmd_Geh());
        commands.add(new cmd_Random());
        commands.add(new cmd_lategame());

        while(!commands.isEmpty()) {
            commandHandler.commands.put(((Command)commands.get(commands.size() - 1)).name(), commands.get(commands.size() - 1));
            System.out.println("Registrating " + ((Command)commands.get(commands.size() - 1)).name());
            commands.remove(commands.get(commands.size() - 1));
        }

    }

    public static void addListeners() {
        builder.addEventListener(new Object[]{new MessageListener()});
        builder.addEventListener(new Object[]{new PermissionListener()});
        builder.addEventListener(new Object[]{new PrivateMessage()});
        builder.addEventListener(new Object[]{new StatusUpdateListener()});
    }
}
