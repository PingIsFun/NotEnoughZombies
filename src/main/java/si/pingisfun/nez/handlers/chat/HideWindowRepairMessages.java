package si.pingisfun.nez.handlers.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.self.WindowRepairEvent;

public class HideWindowRepairMessages {

    @SubscribeEvent
    public void onGoldReceive(WindowRepairEvent windowRepairEvent) {
        if (!ModConfig.hideWindowRepairMessages) {
            return;
        }
        windowRepairEvent.getEvent().setCanceled(true);
    }
}
