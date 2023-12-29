package si.pingisfun.nez.hud;

import cc.polyfrost.oneconfig.config.annotations.Exclude;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.hud.TextHud;
import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.events.player.PowerUpPickupEvent;
import si.pingisfun.nez.utils.JavaUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class PowerUpHud extends TextHud {
    @Exclude
    private final Map<Long, PowerUp> activePowerUps = new TreeMap<>();

    @Switch(name = "Bold power up name")
    boolean boldName = false;

    public PowerUpHud() {
        super(true);
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        if (example) {
            lines.add(PowerUp.SHOPPING_SPREE.getColor() + (boldName ? String.valueOf(ChatColor.BOLD) : "") + PowerUp.SHOPPING_SPREE.getName() + ChatColor.RESET + ChatColor.RED + " 2.6s");
            lines.add(PowerUp.INSTA_KILL.getColor() + (boldName ? String.valueOf(ChatColor.BOLD) : "") + PowerUp.INSTA_KILL.getName() + ChatColor.RESET + ChatColor.GOLD + " 7.3s");
            lines.add(PowerUp.DOUBLE_GOLD.getColor() + (boldName ? String.valueOf(ChatColor.BOLD) : "") + PowerUp.DOUBLE_GOLD.getName() + ChatColor.RESET + ChatColor.GREEN + " 14.8s");
            return;
        }
        for (Map.Entry<Long, PowerUp> entry : activePowerUps.entrySet()) {
            long expireTime = entry.getKey();
            long currentTime = System.currentTimeMillis();

            if (expireTime < currentTime) {
                activePowerUps.remove(entry.getKey());
                break;
            }
            PowerUp powerUp = entry.getValue();
            long timeLeftMs = expireTime - currentTime;
            int timeLefts = (int) (timeLeftMs / 1000);
            ChatColor timeColor;
            if (timeLefts > 10) {
                timeColor = ChatColor.GREEN;
            } else if (timeLefts > 3) {
                timeColor = ChatColor.GOLD;
            } else {
                timeColor = ChatColor.RED;
            }

            lines.add(powerUp.getColor() + (boldName ? String.valueOf(ChatColor.BOLD) : "") + powerUp.getName() + ChatColor.RESET + " " + timeColor + (JavaUtils.msToSeconds1DecString(timeLeftMs)) + "s");
        }
    }

    @SubscribeEvent
    public void addPowerUp(PowerUpPickupEvent powerUpPickupEvent) {
        long expireTime = System.currentTimeMillis() + (1000L * powerUpPickupEvent.getDurationSeconds());

        // TODO: Remove old power up if duplicate
        // Find the correct position to insert the new item
        for (Map.Entry<Long, PowerUp> entry : activePowerUps.entrySet()) {
            if (expireTime > entry.getKey()) {
                // Insert the new item before the current entry
                activePowerUps.put(expireTime, powerUpPickupEvent.getPowerUp());
                return;
            }
        }

        // If the loop completes, the new item has the latest expiration time
        activePowerUps.put(expireTime, powerUpPickupEvent.getPowerUp());
    }
}