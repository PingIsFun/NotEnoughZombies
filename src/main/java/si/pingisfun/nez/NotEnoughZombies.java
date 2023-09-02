package si.pingisfun.nez;

import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import si.pingisfun.nez.command.NEZCommand;
import si.pingisfun.nez.command.TestCommand;
import si.pingisfun.nez.config.ModConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import si.pingisfun.nez.handlers.alert.PowerUpAlert;
import si.pingisfun.nez.handlers.base.*;
import si.pingisfun.nez.handlers.chat.HideGoldMessages;
import si.pingisfun.nez.handlers.chat.HideKnockdownMessages;
import si.pingisfun.nez.handlers.chat.HideReviveMessages;
import si.pingisfun.nez.handlers.chat.HideWindowRepairMessages;
import si.pingisfun.nez.handlers.entity.PowerUpCountdown;
import si.pingisfun.nez.handlers.game.ZombiesGame;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = NotEnoughZombies.MODID, name = NotEnoughZombies.NAME, version = NotEnoughZombies.VERSION)
public class NotEnoughZombies {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static NotEnoughZombies INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static ModConfig config;
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ZombiesGame game;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new ModConfig();
        game = new ZombiesGame();
        CommandManager.INSTANCE.registerCommand(new NEZCommand());
        CommandManager.INSTANCE.registerCommand(new TestCommand());

        // Base Handlers
        MinecraftForge.EVENT_BUS.register(new TitleEventHandler());
        MinecraftForge.EVENT_BUS.register(new ActionBarChatEventHandler());
//        MinecraftForge.EVENT_BUS.register(new PlaySoundEventHandler());
        MinecraftForge.EVENT_BUS.register(new LivingUpdateEventHandler());
        MinecraftForge.EVENT_BUS.register(new EntityJoinWorldHandler());


        // New Game
        MinecraftForge.EVENT_BUS.register(game);
        // Alert
        MinecraftForge.EVENT_BUS.register(new PowerUpAlert());
        // Chat
        MinecraftForge.EVENT_BUS.register(new HideGoldMessages());
        MinecraftForge.EVENT_BUS.register(new HideWindowRepairMessages());
        MinecraftForge.EVENT_BUS.register(new HideReviveMessages());
        MinecraftForge.EVENT_BUS.register(new HideKnockdownMessages());

        // Entity
        MinecraftForge.EVENT_BUS.register(new PowerUpCountdown());


        // Debug
//        MinecraftForge.EVENT_BUS.register(new TestHandler());
    }
}
