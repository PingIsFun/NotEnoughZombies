package si.pingisfun.nez.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class ChatEvent extends Event {
    private final ClientChatReceivedEvent event;

    protected ChatEvent(ClientChatReceivedEvent event) {
        this.event = event;
    }

    public ClientChatReceivedEvent getChatEvent() {
        return event;
    }
}
