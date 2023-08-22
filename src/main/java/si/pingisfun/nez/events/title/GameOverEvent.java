package si.pingisfun.nez.events.title;

import net.minecraftforge.fml.common.eventhandler.Event;

public class GameOverEvent extends Event {
    private final int finalRound;

    public GameOverEvent(int finalRound) {
        this.finalRound = finalRound;
    }

    public int getFinalRound() {
        return finalRound;
    }
}
