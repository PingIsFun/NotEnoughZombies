package si.pingisfun.nez.utils;

import cc.polyfrost.oneconfig.events.event.ChatSendEvent;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.enums.ZombiesMap;
import si.pingisfun.nez.events.*;
import si.pingisfun.nez.events.title.GameStartEvent;
import si.pingisfun.nez.events.title.NewRoundEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
