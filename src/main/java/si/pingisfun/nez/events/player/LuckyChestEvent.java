package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class LuckyChestEvent extends ChatEvent {
    private final String player;
    private final String item;

    public LuckyChestEvent(ClientChatReceivedEvent event, String player, String item) {
        super(event);
        this.player = player;
        this.item = item;
    }

    public String getPlayer() {
        return player;
    }

    public String getItem() {
        return item;
    }
}
