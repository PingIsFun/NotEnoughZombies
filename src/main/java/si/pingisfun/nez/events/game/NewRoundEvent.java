package si.pingisfun.nez.events.game;

import net.minecraftforge.fml.common.eventhandler.Event;

public class NewRoundEvent extends Event {
    private final int round;

    public NewRoundEvent(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
