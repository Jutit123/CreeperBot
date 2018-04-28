package core.permission;

import core.pi.ErrorType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import utils.SECRETS;

import java.io.*;
import java.util.ArrayList;

public class PermissionLoader {

    public static ArrayList<UserPermission> permissions = new ArrayList<>();
    public static ArrayList<UserPermission> permissionsFill = new ArrayList<>();

    public static void fillGuild(Guild g) throws IOException {
        for (Member m : g.getMembers())
            permissionsFill.add(new UserPermission(m.getUser().getId(), g.getId(), (byte) 0));
        toFiles();
    }

    public static void addToFile(String userID, String GuildID, PermissionLevels lvl) throws IOException {

        File file;
        if (SECRETS.PI)
            file = new File("/home/pi/Schreibtisch/XayahBot/perms.txt");
        else
            file = new File(SECRETS.PATH_TO_DESKTOP_DIRECTORY + "perms.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(GuildID + ": " + userID + " - " + lvl.ordinal());
        writer.newLine();
        writer.flush();
        writer.close();
        reload();

    }

    public static UserPermission getPerm(User user, Guild guild){
        for (PermissionLoader.UserPermission ud : PermissionLoader.permissions){
            if (ud.getUserID().equals(user.getId())) {
                if (ud.getGuildID().equals(guild.getId())) {
                    return ud;
                }
            }
        }
        return new UserPermission(user.getId(), guild.getId(), (byte) 0);
    }

    public static void load() {
        String line = "";
        BufferedReader reader = null;
        try {
            File file;
            if (SECRETS.PI)
                file = new File("/home/pi/Schreibtisch/XayahBot/perms.txt");
            else
                file = new File(SECRETS.PATH_TO_DESKTOP_DIRECTORY + "perms.txt");
            reader = new BufferedReader(new FileReader(file));
            while (!line.equals(null)) {
                line = reader.readLine();

                String[] splits = line.split(": ");
                String tmpGuildID = splits[0];

                splits = splits[1].split(" - ");
                String tmpUserID = splits[0];
                byte tmpUserPerm = Byte.parseByte(splits[1]);

                permissions.add(new UserPermission(tmpUserID, tmpGuildID, tmpUserPerm));
            }
        }catch (NullPointerException e){}
        catch (IOException e){
            core.pi.out.error(ErrorType.NOT_FOUND);
            e.printStackTrace();
        }
    }

    public static void reload() throws IOException {
        permissions.clear();
        load();
    }

    private static boolean containsIt = false;

    public static void toFiles() {
        try {
            File file;
            if (SECRETS.PI)
                file = new File("/home/pi/Schreibtisch/XayahBot/perms.txt");
            else
                file = new File(SECRETS.PATH_TO_DESKTOP_DIRECTORY + "perms.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (UserPermission ud : permissionsFill){
                writer.write(ud.guildID + ": " + ud.getUserID() + " - " + ud.getPermissionLevel());
                writer.newLine();
            }
            writer.flush();
            writer.close();
            reload();
        } catch (IOException e) {
            core.pi.out.error(ErrorType.NOT_FOUND);
            e.printStackTrace();
        }
    }

    public static void toFile() throws IOException {
        File file;
        if (SECRETS.PI)
            file = new File("/home/pi/Schreibtisch/XayahBot/perms.txt");
        else
            file = new File(SECRETS.PATH_TO_DESKTOP_DIRECTORY + "perms.txt");

        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (UserPermission ud : permissions){
            writer.write(ud.guildID + ": " + ud.getUserID() + " - " + ud.getPermissionLevel());
            writer.newLine();
        }
        writer.flush();
        writer.close();
        reload();
    }

    public static class UserPermission{

        private final String userID;
        private final String guildID;
        private final byte permissionLevel;

        public UserPermission(String userID, String guildID, byte permissionLevel) {
            this.userID = userID;
            this.guildID = guildID;
            this.permissionLevel = permissionLevel;
        }

        public String getUserID() {
            return userID;
        }

        public String getGuildID() {
            return guildID;
        }

        public byte getPermissionLevel() {
            return permissionLevel;
        }
    }

}
