package si.pingisfun.nez.handlers.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.chat.GoldReceiveEvent;
import si.pingisfun.nez.events.game.OpenAreaEvent;
import si.pingisfun.nez.events.player.*;
import si.pingisfun.nez.events.self.ItemPurchaseEvent;
import si.pingisfun.nez.events.self.WindowRepairEvent;

public class HideMessages {
    @SubscribeEvent
    public void onGoldReceive(GoldReceiveEvent event) {
        if (!ModConfig.hideGoldMessages) {
            return;
        }
        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onKnockdownEvent(PlayerKnockdownEvent event) {
        if (!ModConfig.hideKnockdownMessages) {
            return;
        }
        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onReviveEvent(ReviveEvent event) {
        if (!ModConfig.hideReviveMessages) {
            return;
        }
        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onGoldReceive(WindowRepairEvent event) {
        if (!ModConfig.hideWindowRepairMessages) {
            return;
        }
        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onHitTargetEvent(HitTargetEvent event) {
        if (!ModConfig.hideHitTargetMessages) {
            return;
        }

        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onLuckyChestEvent(LuckyChestEvent event) {
        if (!ModConfig.hideLuckyChestMessages) {
            return;
        }

        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onOpenAreaEvent(OpenAreaEvent event) {
        if (!ModConfig.hideOpenAreaMessages) {
            return;
        }

        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerConnectionStatusEvent(PlayerConnectionStatusEvent event) {
        if (!ModConfig.hidePlayerConnectionStatusMessages) {
            return;
        }

        event.getChatEvent().setCanceled(true);
    }

    @SubscribeEvent
    public void onPowerUpPickupEvent(PowerUpPickupEvent event) {
        if (!ModConfig.hidePowerUpPickupMessages) {
            return;
        }

        event.getChatEvent().setCanceled(true);
    }



}
