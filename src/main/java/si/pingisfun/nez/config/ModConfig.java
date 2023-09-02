package si.pingisfun.nez.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import si.pingisfun.nez.NotEnoughZombies;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class ModConfig extends Config {
    /*
     * General
     */

    @DualOption(
            name = "Prefix",
            left = "Not Enough Zombies",
            right = "NEZ",
            size = OptionSize.DUAL
    )
    public static boolean shortPrefix = false;

    @Dropdown(
            name = "Alert on power up spawn",
            options = {"Off", "Self", "Party", "Chat"}
    )
    public static int powerUpSpawnAlert = 1;

    @Dropdown(
            name = "Alert on power up despawn",
            options = {"Off", "Self", "Party", "Chat"}
    )
    public static int powerUpDeSpawnAlert = 1;

    @Switch(
            name = "Show despawn countdown next to the power up"
    )
    public static boolean powerupCountdown = false;


    /*
     * Chat
     */

    @Switch(
            name = "Hide Gold Received Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideGoldMessages = false;

    @Switch(
            name = "Hide Window Repair Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideWindowRepairMessages = false;

    @Switch(
            name = "Hide Revive Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideReviveMessages = false;

    @Switch(
            name = "Hide Knockdown Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideKnockdownMessages = false;

    public ModConfig() {
        super(new Mod(NotEnoughZombies.NAME, ModType.HYPIXEL), NotEnoughZombies.MODID + ".json");
        initialize();
    }
}

