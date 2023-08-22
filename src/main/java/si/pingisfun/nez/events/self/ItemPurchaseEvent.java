package si.pingisfun.nez.events.self;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ItemPurchaseEvent extends Event {
    private final String item;

    public ItemPurchaseEvent(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
