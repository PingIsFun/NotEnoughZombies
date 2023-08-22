package si.pingisfun.nez.utils;

import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import scala.Int;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.ZombiesMap;

import java.util.*;
import java.util.stream.Stream;

public class ZombiesUtils {
    public static boolean isZombiesGame() {
        try {
            return HypixelUtils.INSTANCE.isHypixel() && LocrawUtil.INSTANCE.getLocrawInfo().getGameMode().startsWith("ZOMBIES_");
        } catch (NullPointerException ignored) {
            return false;
        }
    }
    public static ZombiesMap getMap() {
        if (!isZombiesGame()) {
            return null;
        }
        return ZombiesMap.getMapByName(LocrawUtil.INSTANCE.getLocrawInfo().getMapName());
    }

    public static Integer getRoundFromSidebar() {
        if (!isZombiesGame()) {
            return null;
        }
        String line = MinecraftUtils.removeFormatting(MinecraftUtils.getSitebarLine(12)); // "Round 22"
        String num = line.replace("Round", "").replace(" ", ""); // "22"
        return Integer.parseInt(num);
    }
    public static Integer getZombiesLeftFromSidebar() {
        if (!isZombiesGame()) {
            return null;
        }
        String line = MinecraftUtils.removeFormatting(MinecraftUtils.getSitebarLine(11)); // "Zombies Left: 11"
        String num = line.split(":")[1].replace(" ", ""); // "11"
        return Integer.parseInt(num);
    }

    public static List<String> getPlayersFromSidebar() {
        if (!isZombiesGame()) {
            return null;
        }
        final int PLAYER_LINES_START = 6;
        final int PLAYER_LINES_END = 9;

        ArrayList<String> players = new ArrayList<>(4);
        for (int i = PLAYER_LINES_START; i <= PLAYER_LINES_END; i++) {
            String line = MinecraftUtils.removeFormatting(MinecraftUtils.getSitebarLine(i)); // "PingIsFun: 1000"
            String name = line.split(":")[0].replace(" ", "");
            players.add(name);
        }
        return players;
    }

    public static Integer getPlaytimeInSeconds() {
        if (!isZombiesGame()) {
            return null;
        }
        String line = MinecraftUtils.removeFormatting(MinecraftUtils.getSitebarLine(3)); // "Time: 1:20:14"
        String time = line.split(": ")[1].replace(" ", ""); // "1:20:14"
        String[] parts = time.split(":");
        return Stream.of(parts)
                .mapToInt(Integer::parseInt)
                .reduce(0, (acc, value) -> acc * 60 + value);
    }
}
