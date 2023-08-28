package si.pingisfun.nez.events.player;

import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.enums.PowerUp;

public class PowerUpPickupEvent extends Event {
    private final String pickupPlayer;
    private final PowerUp powerUp;
    private final int duration;

    public PowerUpPickupEvent(String pickupPlayer, PowerUp powerUp, int timeLeft) {
        this.pickupPlayer = pickupPlayer;
        this.powerUp = powerUp;
        this.duration = timeLeft;
    }

    public String getPickupPlayer() {
        return pickupPlayer;
    }

    public int getDuration() {
        return duration;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
