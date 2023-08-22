package si.pingisfun.nez.events.player;

import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerDownEvent extends Event {
    private final String player;
    private final String killer;
    private final String location;
    private final int timeLeft;
    private final boolean self;

    public PlayerDownEvent(String player, String killer, String location, int timeLeft, boolean self) {
        this.player = player;
        this.killer = killer;
        this.location = location;
        this.timeLeft = timeLeft;
        this.self = self;
    }


    public String getPlayer() {
        return player;
    }

    public String getKiller() {
        return killer;
    }

    public String getLocation() {
        return location;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public boolean isSelf() {
        return self;
    }
}
