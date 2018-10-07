//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package listener;

import core.pi.out;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.SECRETS;

public class StatusUpdateListener extends ListenerAdapter {
    public StatusUpdateListener() {
    }

    public void onUserUpdateGame(UserUpdateGameEvent event) {
        if (event.getNewGame() != null) {
            if (event.getNewGame().getType() == GameType.STREAMING) {
                try {
                    event.getGuild().getDefaultChannel().sendMessage((new EmbedBuilder())
                            .setTitle("NEUER STREAM VON " + event.getMember().getEffectiveName())
                            .setColor(SECRETS.RAKAN_PURPLE)
                            .setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl())
                            .setDescription(event.getNewGame().getName() + "\nSchau vorbei: " + event.getNewGame().getUrl())
                            .build()).queue();
                    if (SECRETS.PI && event.getMember().getEffectiveName().equals("winzi")) {
                        out.isLive(true);
                    }
                } catch (NullPointerException var3) {
                    System.out.println("Please create a default Channel!");
                }
            }

            if (event.getOldGame().getType().equals(GameType.STREAMING) && SECRETS.PI && event.getMember().getEffectiveName().equals("winzi")) {
                out.isLive(false);
            }
        }

    }
}
