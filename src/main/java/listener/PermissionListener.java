package listener;

import core.permission.PermissionLevels;
import core.permission.PermissionLoader;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.IOException;

public class PermissionListener extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        try {
            PermissionLoader.addToFile(event.getUser().getId(), event.getGuild().getId(), PermissionLevels.USER);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        try {
            PermissionLoader.addToFile(event.getUser().getId(), event.getGuild().getId(), PermissionLevels.USER);
        } catch (IOException e) { e.printStackTrace(); }
    }

}
