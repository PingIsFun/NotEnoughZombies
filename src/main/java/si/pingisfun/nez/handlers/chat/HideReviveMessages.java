package si.pingisfun.nez.handlers.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.player.ReviveEvent;

public class HideReviveMessages {
    @SubscribeEvent
    public void onReviveEvent(ReviveEvent reviveEvent) {
        if (!ModConfig.hideReviveMessages) {
            return;
        }
        reviveEvent.getChatEvent().setCanceled(true);
    }
}
