package si.pingisfun.nez.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import si.pingisfun.nez.config.ModConfig;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see ModConfig#hud
 */
public class TestHud extends SingleTextHud {
    public TestHud() {
        super("Test", true);
    }

    @Override
    public String getText(boolean example) {
        return "I'm an example HUD";
    }
}
