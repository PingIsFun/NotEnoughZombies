package si.pingisfun.nez.handlers.updater;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.updater.CheckForModUpdate;
import si.pingisfun.nez.utils.ChatUtil;

public class CheckForUpdates {
    private boolean updateCheckTriggered = false;
    private static final String LATEST_URL = "https://github.com/PingIsFun/NotEnoughZombies/releases/latest";
    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent worldLoadEvent) {
        // Check for updates
        if (!updateCheckTriggered && NotEnoughZombies.minecraft.theWorld != null && NotEnoughZombies.minecraft.thePlayer != null) {
            updateCheckTriggered = true;
            if (!CheckForModUpdate.isModUpToDate(NotEnoughZombies.VERSION)) {
                UChat.chat(new UTextComponent(ChatUtil.getPrefix() + ChatColor.RED + "You are using an outdated version of the mod."));
                UChat.chat(new UTextComponent(ChatUtil.getPrefix() + ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "GET THE LATEST VERSION" + ChatColor.DARK_GRAY + "]")
                        .setChatStyle(new ChatStyle()
                                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, LATEST_URL))
                                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(ChatColor.YELLOW + LATEST_URL)))));
            }
        }
    }
}
