package si.pingisfun.nez.utils;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import net.minecraft.client.Minecraft;
import si.pingisfun.nez.NotEnoughZombies;

public class ChatUtil {
    static final String PREFIX = ChatColor.DARK_GREEN + "[" + ChatColor.GOLD + NotEnoughZombies.NAME + ChatColor.DARK_GREEN + "] " + ChatColor.RESET;
    public static void printFormat(String data) {
        UChat.chat(ChatColor.Companion.translateAlternateColorCodes('&', data));
    }

    public static void printRaw(String data) {
        UChat.chat(data);
    }
    public static void printError(String data) {
        UChat.chat(ChatColor.RED + "[" + NotEnoughZombies.NAME + "] " + data);
    }

    public static void printMessage(String data) {
        UChat.chat(PREFIX + data);
    }

    public static void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

}
