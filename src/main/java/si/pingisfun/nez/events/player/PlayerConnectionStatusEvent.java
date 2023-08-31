package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.enums.PlayerConnectionStatus;
import si.pingisfun.nez.events.ChatEvent;

public class PlayerConnectionStatusEvent extends ChatEvent {
    private final String player;
    private final PlayerConnectionStatus status;

    public PlayerConnectionStatusEvent(ClientChatReceivedEvent event, String player, PlayerConnectionStatus status) {
        super(event);
        this.player = player;
        this.status = status;
    }

    public String getPlayer() {
        return player;
    }

    public PlayerConnectionStatus getStatus() {
        return status;
    }
}
