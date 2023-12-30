package si.pingisfun.nez.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import si.pingisfun.nez.NotEnoughZombies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MinecraftUtils {
    private static final String NAME_SEPARATOR = "\uD83C\uDF82";// "🎂";

    public static String removeFormatting(String text) {
        return text.replaceAll("§.", "");
    }

    /**
     * Gets sidebar lines on Hypixel.
     *
     * @return the sidebar lines
     */
    public static List<String> getSidebarLines() {
        List<String> lines = new ArrayList<>();
        Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        if (scoreboard == null) {
            return lines;
        }

        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);

        if (objective == null) {
            return lines;
        }

        Collection<Score> scores = scoreboard.getSortedScores(objective);
        ArrayList<Score> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#")).collect(Collectors.toCollection(Lists::newArrayList));

        if (list.size() > 15) {
            list = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
        }
        final String[] scoreboardHolders = {"🎂", "🎉", "🎁", "👹", "🏀", "⚽", "🍭", "🌠", "👾", "🐍", "🔮", "👽", "💣", "🍫", "🔫"};


        for (int i = 0; i < list.size(); i++) {
            Score score = list.get(i);
            String holder = scoreboardHolders[i];
            ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            String line = ScorePlayerTeam.formatPlayerName(team, score.getPlayerName());
            lines.add(line.replace(holder, ""));
        }

        return lines;
    }

    public static String getSitebarLine(int line) {
        return getSidebarLines().get(line);
    }

    public static String removeCustomTextFromName(String name) {
        if (!name.contains(NAME_SEPARATOR)) {
            return name;
        }
        return name.split(NAME_SEPARATOR)[0];
    }

    public static boolean isNearby(Entity entity, double maxDistance) {
        BlockPos playerPos = Minecraft.getMinecraft().thePlayer.getPosition();
        BlockPos entityPos = entity.getPosition();
        double distance = Math.sqrt(playerPos.distanceSq(entityPos));
        return distance <= maxDistance;
    }

}

