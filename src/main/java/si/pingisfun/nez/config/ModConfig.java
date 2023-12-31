package si.pingisfun.nez.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import cc.polyfrost.oneconfig.libs.universal.UKeyboard;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.hud.PowerUpHud;
import si.pingisfun.nez.hud.timestamp.GameTimeHUD;
import si.pingisfun.nez.hud.timestamp.RoundTimeHUD;
import si.pingisfun.nez.utils.ChatUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Dropdown(
            name = "Next power up round alert",
            options = {"Off", "Self", "Party", "Chat"}
    )
    public static int nextPowerUpRoundAlert = 1;

    @Switch(
            name = "Show despawn countdown next to the power up"
    )
    public static boolean powerupCountdown = false;

    @HUD(
            name = "Power up timer",
            category = "HUD"
    )
    public PowerUpHud powerUpHud = new PowerUpHud();
    @HUD(
            name = "Game Timer",
            category = "HUD"
    )
    public GameTimeHUD gameTimer = new GameTimeHUD();
    @HUD(
            name = "Round Timer",
            category = "HUD"
    )
    public RoundTimeHUD roundTime = new RoundTimeHUD();

    /*
     * Visibility
     */
    @Switch(
            name = "Hide nearby entities",
            category = "Visibility"

    )
    public static boolean hideNearbyEntities = false;

    @KeyBind(
            name = "",
            category = "Visibility"
    )
    public static OneKeyBind keyBindHideNearbyEntities = new OneKeyBind(UKeyboard.KEY_I);

    @Switch(
            name = "Hide nearby players",
            category = "Visibility"

    )
    public static boolean hideNearbyPlayers = false;
    @KeyBind(
            name = "",
            category = "Visibility"
    )
    public static OneKeyBind keyBindhideNearbyPlayers = new OneKeyBind(UKeyboard.KEY_NONE);


    /*
     * Chat
     */

    // Hide Messages subcategory
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

    @Switch(
            name = "Hide Target Hit Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideHitTargetMessages = false;

    @Switch(
            name = "Hide Lucky Chest Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideLuckyChestMessages = false;

    @Switch(
            name = "Hide Open Area Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hideOpenAreaMessages = false;

    @Switch(
            name = "Hide Player Leave/Rejoin  Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hidePlayerConnectionStatusMessages = false;

    @Switch(
            name = "Hide Power Up Pickup  Messages",
            category = "Chat",
            subcategory = "Hide Messages"
    )
    public static boolean hidePowerUpPickupMessages = false;


    public ModConfig() {
        super(new Mod(NotEnoughZombies.NAME, ModType.HYPIXEL), NotEnoughZombies.MODID + ".json");
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        registerKeyBind(keyBindHideNearbyEntities, () -> ChatUtil.message("keybind pressed"+sdfDate.format(new Date())));
        initialize();
    }
}

