package si.pingisfun.nez.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.enums.ZombiesMap;
import si.pingisfun.nez.events.game.GameStartEvent;
import si.pingisfun.nez.events.game.NewRoundEvent;

public class GameInfo {
    ZombiesMap map;
    int currentRound;
    int[][] maxAmmoPattern;
    int[][] instaKillPattern;
    int[][] shoppingSpreePattern;

    @SubscribeEvent
    public void onStart(GameStartEvent startEvent) {
        this.map = ZombiesUtils.getMap();
        this.currentRound = 1;
        ChatUtil.printMessage("Zombies game started: " + this.map.getName());
    }

    @SubscribeEvent
    public void onNewRound(NewRoundEvent newRoundEvent) {
        this.currentRound = newRoundEvent.getRound();
        ChatUtil.printMessage("New Round: " + this.currentRound);
    }
}
