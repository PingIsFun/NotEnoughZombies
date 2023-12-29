package si.pingisfun.nez.events.player;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.events.ChatEvent;

public class PowerUpPickupEvent extends ChatEvent {
    private final String pickupPlayer;
    private final PowerUp powerUp;
    private final int duration;

    public PowerUpPickupEvent(ClientChatReceivedEvent event, String pickupPlayer, PowerUp powerUp, int timeLeft) {
        super(event);
        this.pickupPlayer = pickupPlayer;
        this.powerUp = powerUp;
        this.duration = timeLeft;
    }

    public String getPickupPlayer() {
        return pickupPlayer;
    }

    public int getDurationSeconds() {
        return duration;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
