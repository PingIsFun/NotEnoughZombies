package si.pingisfun.nez.handlers.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.chat.GoldReceiveEvent;

public class HideGoldMessages {

    @SubscribeEvent
    public void onGoldReceive(GoldReceiveEvent goldReceiveEvent) {
        if (!ModConfig.hideGoldMessages) {
            return;
        }
        goldReceiveEvent.getEvent().setCanceled(true);
    }
}
