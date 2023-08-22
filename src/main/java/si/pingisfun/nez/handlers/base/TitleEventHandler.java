package si.pingisfun.nez.handlers.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.events.game.GameOverEvent;
import si.pingisfun.nez.events.game.GameStartEvent;
import si.pingisfun.nez.events.game.NewRoundEvent;
import si.pingisfun.nez.events.title.*;
import si.pingisfun.nez.utils.JavaUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

public class TitleEventHandler {
    @SubscribeEvent
    public void handleTitleEvent(TitleEvent titleEvent) {
        String[][] titleMatrix = {
                {"newRound", "§cRound (\\d*)§r", "§r"},
                {"gameOver", "§cGame Over!§r", "§7You made it to Round (\\d*)!§r", },
        };

        String[] titleMatch = {titleEvent.getTitle(), titleEvent.getSubtitle()};
        Map.Entry<String, List<Matcher>> matrixRes = JavaUtils.matchRegexMatrix(titleMatrix, titleMatch);
        if (Objects.isNull(matrixRes)) {
            return;
        }

        String type = matrixRes.getKey();

        Matcher titleMatcher = matrixRes.getValue().get(0);
        Matcher subtitleMatcher = matrixRes.getValue().get(1);

        EventBus bus = MinecraftForge.EVENT_BUS;
        switch (type) {
            case "newRound": {
                int round = Integer.parseInt(titleMatcher.group(1));
                bus.post(new NewRoundEvent(round));
                if (round == 1) {
                    bus.post(new GameStartEvent());
                }
                break;
            }
            case "gameOver": {
                int round = Integer.parseInt(subtitleMatcher.group(1));
                bus.post(new GameOverEvent(round));
                break;
            }
        }
    }
}
