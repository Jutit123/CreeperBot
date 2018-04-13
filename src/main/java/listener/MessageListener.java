package listener;

import core.commandHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.SECRETS;

public class MessageListener extends ListenerAdapter{

    private commandHandler commandHandler;

    public void onMessageReceived(MessageReceivedEvent event){

        try {
            if (event.getMessage().getContentRaw().startsWith(SECRETS.PREFIX) &&
                    ! (event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId()))) {
                commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw().toLowerCase(), event));
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

}
