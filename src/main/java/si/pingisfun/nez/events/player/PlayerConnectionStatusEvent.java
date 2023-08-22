package si.pingisfun.nez.events.player;

import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.enums.PlayerConnectionStatus;

public class PlayerConnectionStatusEvent extends Event {
    private final String player;
    private final PlayerConnectionStatus status;

    public PlayerConnectionStatusEvent(String player, PlayerConnectionStatus status) {
        this.player = player;
        this.status = status;
    }

    public String getPlayer() {
        return player;
    }

    public PlayerConnectionStatus getStatus() {
        return status;
    }

    public static class OpenAreaEvent extends Event {
        private final String player;
        private final String area;

        public OpenAreaEvent(String player, String area) {
            this.player = player;
            this.area = area;
        }

        public String getArea() {
            return area;
        }

        public String getPlayer() {
            return player;
        }
    }
}
