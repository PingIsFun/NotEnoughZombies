package si.pingisfun.nez.events.player;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ReviveEvent extends Event {
    private final String reviver;
    private final String revivedPlayer;

    public ReviveEvent(String reviver, String revivedPlayer) {
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
