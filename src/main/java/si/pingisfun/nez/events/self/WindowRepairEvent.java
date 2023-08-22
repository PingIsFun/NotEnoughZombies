package si.pingisfun.nez.events.self;

import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.enums.WindowRepair;

public class WindowRepairEvent extends Event {
    private final WindowRepair type;
    private final Event event;

    public WindowRepairEvent(Event event, WindowRepair type) {
        this.type = type;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public WindowRepair getType() {
        return type;
    }

}
