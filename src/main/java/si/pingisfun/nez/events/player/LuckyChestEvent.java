package si.pingisfun.nez.events.player;

import net.minecraftforge.fml.common.eventhandler.Event;

public class LuckyChestEvent extends Event {
    private final String player;
    private final String item;

    public LuckyChestEvent(String player, String item) {
        this.player = player;
        this.item = item;
    }

    public String getPlayer() {
        return player;
    }

    public String getItem() {
        return item;
    }

    public static class HitTargetEvent extends Event {
        private final String player;

        public HitTargetEvent(String player) {
            this.player = player;
        }

        public String getPlayer() {
            return player;
        }

    }
}
