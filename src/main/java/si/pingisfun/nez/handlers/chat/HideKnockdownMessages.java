package si.pingisfun.nez.handlers.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.player.PlayerKnockdownEvent;

public class HideKnockdownMessages {
    @SubscribeEvent
    public void onKnockdownEvent(PlayerKnockdownEvent knockdownEvent) {
        if (!ModConfig.hideKnockdownMessages) {
            return;
        }
        knockdownEvent.getChatEvent().setCanceled(true);
    }
}
