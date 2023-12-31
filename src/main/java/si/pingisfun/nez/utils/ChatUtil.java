package si.pingisfun.nez.utils;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import net.minecraft.client.Minecraft;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.enums.config.ChatOutput;

import java.util.Map;

public class ChatUtil {
    static final String PREFIX = ChatColor.DARK_GREEN + "[" + ChatColor.GOLD + NotEnoughZombies.NAME + ChatColor.DARK_GREEN + "] " + ChatColor.RESET;
    static final String SHORT_PREFIX = ChatColor.DARK_GREEN + "[" + ChatColor.GOLD + NotEnoughZombies.MODID.toUpperCase() + ChatColor.DARK_GREEN + "] " + ChatColor.RESET;

    public static String getPrefix() {
        if (ModConfig.shortPrefix) {
            return SHORT_PREFIX;
        } else {
            return PREFIX;
        }
    }

    public static void printFormat(String data) {
        UChat.chat(ChatColor.Companion.translateAlternateColorCodes('&', data));
    }

    public static void printRaw(String data) {
        UChat.chat(data);
    }

    public static void printError(String data) {
        UChat.chat(getPrefix() + ChatColor.RED + data);
    }

    public static void message(String data) {
        message(data, ChatOutput.SELF);
    }

    public static void message(String data, ChatOutput output) {
        switch (output) {
            case OFF: {
                break;
            }
            case SELF: {
                UChat.chat(getPrefix() + data);
                break;
            }
            case PARTY: {
                sendChatMessage("/pc " + data);
                break;
            }
            case CHAT: {
                sendChatMessage("/ac " + data);
                break;
            }
        }
    }


    public static void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }


    public static void sendLuckyChestRollDataToChat() {
        ChatUtil.message("Lucky Chest Roll Data:");
        for (Map.Entry<String, Map<String, Integer>> outerEntry : NotEnoughZombies.game.getLuckyChestRollData().entrySet()) {
            StringBuilder result = new StringBuilder();

            // Indentation based on the level
            result.append(ChatColor.GOLD).append(outerEntry.getKey()).append(": ");

            Map<String, Integer> innerMap = outerEntry.getValue();
            int c = 0;
            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                // Indentation for subentries
                result.append(ChatColor.WHITE)
                        .append(innerEntry.getKey())
                        .append(": ")
                        .append(innerEntry.getValue());

                if (c >= innerMap.size() - 1) {
                    break;
                }
                result.append(", ");

                c++;
            }
            ChatUtil.message(result.toString(), ChatOutput.SELF);
        }
    }
}
