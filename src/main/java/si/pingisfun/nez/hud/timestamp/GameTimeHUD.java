package si.pingisfun.nez.hud.timestamp;

import si.pingisfun.nez.NotEnoughZombies;

public class GameTimeHUD extends TimestampHUD {
    public GameTimeHUD() {
        super("Game Time", true);
    }

    @Override
    protected long getTimestampMs() {
        return NotEnoughZombies.game.getGameStartTimestampMs();
    }
}
