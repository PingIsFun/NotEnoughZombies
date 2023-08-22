package si.pingisfun.nez.events.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GoldReceiveEvent extends Event implements ChatEvent {
    private final ClientChatReceivedEvent event;
    private final int amount;

    public GoldReceiveEvent(ClientChatReceivedEvent event, int amount) {
        this.event = event;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public ClientChatReceivedEvent getEvent() {
        return event;
    }
}
