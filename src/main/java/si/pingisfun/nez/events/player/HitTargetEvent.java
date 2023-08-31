package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class HitTargetEvent extends ChatEvent {
    private final String player;

    public HitTargetEvent(ClientChatReceivedEvent event, String player) {
        super(event);
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }
}
