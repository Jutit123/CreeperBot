//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package listener;

import core.Main;
import java.util.Iterator;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.PRIVATE;

public class PrivateMessage extends ListenerAdapter {
    public PrivateMessage() {
    }

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().getId().equalsIgnoreCase(PRIVATE.OWNER_ID)) {
            try {
                String[] args = event.getMessage().getContentRaw().split(" ");
                VoiceChannel userLocated = null;
                Member memberTarget = null;
                Iterator var5 = Main.controller.getGuild().getVoiceChannels().iterator();

                while(var5.hasNext()) {
                    VoiceChannel c = (VoiceChannel)var5.next();
                    Iterator var7 = c.getMembers().iterator();

                    while(var7.hasNext()) {
                        Member m = (Member)var7.next();
                        if (m.getUser().getId().equalsIgnoreCase(args[1])) {
                            userLocated = c;
                        }
                    }
                }

                if (args[0].contains("moveall")) {
                    var5 = Main.controller.getGuild().getVoiceChannelById(userLocated.getId().toString()).getMembers().iterator();

                    while(var5.hasNext()) {
                        Member m = (Member)var5.next();
                        Main.controller.moveVoiceMember(m, Main.controller.getGuild().getVoiceChannelById(args[2])).queue();
                    }
                }
            } catch (Exception var9) {
                event.getChannel().sendMessage("***ERROR***\n" + var9 + "\n\nmoveall [targetUserID] [movedToChannelID]").queue();
            }

        }
    }
}
