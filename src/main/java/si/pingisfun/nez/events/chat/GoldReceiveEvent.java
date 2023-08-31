package si.pingisfun.nez.events.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class GoldReceiveEvent extends ChatEvent {
    private final int amount;

    public GoldReceiveEvent(ClientChatReceivedEvent event, int amount) {
        super(event);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
