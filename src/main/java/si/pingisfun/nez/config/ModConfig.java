package si.pingisfun.nez.config;

import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.hud.TestHud;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class ModConfig extends Config {
    @Switch(
            name = "Hide Gold Received Messages",
            size = OptionSize.SINGLE // Optional
    )
    public static boolean hideGoldMessages = false; // The default value for the boolean Switch.
    @Switch(
            name = "Hide Window Repair Messages",
            size = OptionSize.SINGLE // Optional
    )
    public static boolean hideWindowRepairMessages = false; // The default value for the boolean Switch.
    @Switch(
            name = "ENTITY DEBUG",
            size = OptionSize.SINGLE // Optional
    )
    public static boolean entityDebug = false; // The default value for the boolean Switch.
    @Slider(
            name = "TEST",
            step = 1,
            min = 0, max = 10

    )
    public static int test = 0; // The default value for the boolean Switch.

    public ModConfig() {
        super(new Mod(NotEnoughZombies.NAME, ModType.HYPIXEL), NotEnoughZombies.MODID + ".json");
        initialize();
    }
}

