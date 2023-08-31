package si.pingisfun.nez.events.self;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.events.ChatEvent;

public class ItemPurchaseEvent extends ChatEvent {
    private final String item;

    public ItemPurchaseEvent(ClientChatReceivedEvent event,String item) {
        super(event);
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
