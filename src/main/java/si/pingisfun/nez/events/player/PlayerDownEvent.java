package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.events.ChatEvent;

public class PlayerDownEvent extends ChatEvent {
    private final String player;
    private final String killer;
    private final String location;
    private final int timeLeft;
    private final boolean self;

    public PlayerDownEvent(ClientChatReceivedEvent event, String player, String killer, String location, int timeLeft, boolean self) {
        super(event);
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
