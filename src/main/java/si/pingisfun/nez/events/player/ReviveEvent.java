package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class ReviveEvent extends ChatEvent {
    private final String reviver;
    private final String revivedPlayer;

    public ReviveEvent(ClientChatReceivedEvent event, String reviver, String revivedPlayer) {
        super(event);
        this.reviver = reviver;
        this.revivedPlayer = revivedPlayer;
    }

    public String getReviver() {
        return reviver;
    }

    public String getRevivedPlayer() {
        return revivedPlayer;
    }
}
