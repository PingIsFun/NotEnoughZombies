package si.pingisfun.nez.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import si.pingisfun.nez.NotEnoughZombies;

import java.util.List;

import static si.pingisfun.nez.utils.MinecraftUtils.getSidebarLines;

public class DevUtils {
    public static void debugScoreboard() {
        Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
        NotEnoughZombies.LOGGER.info(scoreboard);
        List<String> lines = getSidebarLines();
        for (int i = 0; i < lines.size(); i++) {
            String rawLine = lines.get(i);
            String line = MinecraftUtils.removeFormatting(rawLine);
            NotEnoughZombies.LOGGER.info(i + " " + rawLine);
            NotEnoughZombies.LOGGER.info(i + " " + line);

        }
    }
}
