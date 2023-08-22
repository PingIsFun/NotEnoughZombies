package si.pingisfun.nez.command;

import cc.polyfrost.oneconfig.libs.checker.units.qual.N;
import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.*;
import si.pingisfun.nez.NotEnoughZombies;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import si.pingisfun.nez.utils.ChatUtil;
import si.pingisfun.nez.utils.MinecraftUtils;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static si.pingisfun.nez.utils.MinecraftUtils.getSidebarLines;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see NotEnoughZombies
 */
@Command(value = NotEnoughZombies.MODID, description = "Access the " + NotEnoughZombies.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        ChatUtil.printMessage("test");
    }
}