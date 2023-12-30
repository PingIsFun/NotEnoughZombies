package si.pingisfun.nez.hud.timestamp;

import si.pingisfun.nez.NotEnoughZombies;

public class RoundTimeHUD extends TimestampHUD {
    public RoundTimeHUD() {
        super("Round Time", true);
    }

    @Override
    protected long getTimestampMs() {
        return NotEnoughZombies.game.getRoundStartTimestampMs();
    }
}
