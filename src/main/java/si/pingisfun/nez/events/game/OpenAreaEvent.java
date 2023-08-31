package si.pingisfun.nez.events.game;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class OpenAreaEvent extends ChatEvent {
    private final String player;
    private final String area;

    public OpenAreaEvent(ClientChatReceivedEvent event, String player, String area) {
        super(event);
        this.player = player;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public String getPlayer() {
        return player;
    }
}
