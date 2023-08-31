package si.pingisfun.nez.events.self;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.enums.WindowRepair;
import si.pingisfun.nez.events.ChatEvent;

public class WindowRepairEvent extends ChatEvent {
    private final WindowRepair type;
    public WindowRepairEvent(ClientChatReceivedEvent event, WindowRepair type) {
        super(event);
        this.type = type;
    }

    public WindowRepair getType() {
        return type;
    }

}
