package si.pingisfun.nez.handlers.base;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PlayerConnectionStatus;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.enums.WindowRepair;
import si.pingisfun.nez.events.chat.GoldReceiveEvent;
import si.pingisfun.nez.events.player.*;
import si.pingisfun.nez.events.self.ItemPurchaseEvent;
import si.pingisfun.nez.events.self.WindowRepairEvent;
import si.pingisfun.nez.utils.JavaUtils;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionBarChatEventHandler {
    private static final String[][] CHAT_REGEX_MATRIX = {
            {"knockedDown", "([a-zA-Z0-9_]{3,16}) was knocked down by (.+) in (.+)! You have (\\d+)s to (get revived|revive them)!"},
            {"revived", "([a-zA-Z0-9_]{3,16}) revived ([a-zA-Z0-9_]{3,16})!"},
            {"powerUp", "([a-zA-Z0-9_]{3,16}) activated (Shopping Spree|Double Gold|Max Ammo|Insta Kill|Carpenter|Bonus Gold)(?:| for (\\d+)s)!"},
            {"foundInLChest", "([a-zA-Z0-9_]{3,16}) found (.+) in the Lucky Chest!"},
            {"playerLeft", "([a-zA-Z0-9_]{3,16}) left the game\\."},
            {"playerRejoined", "([a-zA-Z0-9_]{3,16}) rejoined\\."},
            {"openArea", "([a-zA-Z0-9_]{3,16}) opened (.+)!"},
            {"itemPurchase", "You purchased (.+)!"},
            {"hitTarget", " ⊕ ([a-zA-Z0-9_]{3,16}) hit the target!"},
            {"receiveGold", "\\+(\\d+) Gold(?: \\((.+)\\)|)"},
            {"startRepairing", "Repairing windows\\. Keep holding SNEAK to continue repairing\\."},
            {"stopRepairing", "Stopped repairing\\. There are enemies nearby!"},
            {"stopRepairing", "Stopped repairing\\. Stay within range of the window to repair it!"},
            {"enemyNearby", "You can't repair windows while enemies are nearby!"},
            {"finishRepairing", "You have fully repaired this window!"}
    };
    int oldActionBarHash = 0;

    @SubscribeEvent
    public void handleChatEvent(ClientChatReceivedEvent event) {
        if (!NotEnoughZombies.config.enabled || !ZombiesUtils.isZombiesGame()) {
            return;
        }

        // Actionbar
        if (event.type == 2) {
            int eventHash = Objects.hash(event.type, event.message.getFormattedText());
            if (eventHash == oldActionBarHash) {
                return;
            }
            oldActionBarHash = eventHash;


        } else {
            // Chat
            NotEnoughZombies.LOGGER.info("{" + event.type + "} \"" + event.message.getUnformattedText());

            Map.Entry<String, List<Matcher>> matrixRes = JavaUtils.matchRegexMatrix(CHAT_REGEX_MATRIX, event.message.getUnformattedText());
            if (Objects.isNull(matrixRes)) {
                return;
            }

            String type = matrixRes.getKey();
            Matcher chatMatcher = matrixRes.getValue().get(0);

            EventBus bus = MinecraftForge.EVENT_BUS;
            NotEnoughZombies.LOGGER.info("CHAT TYPE: " + type);
            switch (type) {
                case "knockedDown": {
                    String player = chatMatcher.group(1);
                    String killer = chatMatcher.group(2);
                    String location = chatMatcher.group(3);
                    int timeLeft = Integer.parseInt(chatMatcher.group(4));
                    boolean self = chatMatcher.group(5).equals("get revived");
                    bus.post(new PlayerDownEvent(player, killer, location,timeLeft,self));
                    break;
                }
                case "revived": {
                    String reviver = chatMatcher.group(1);
                    String revivedPlayer = chatMatcher.group(2);
                    bus.post(new ReviveEvent(reviver, revivedPlayer));
                    break;
                }
                case "powerUp": {
                    String player = chatMatcher.group(1);
                    Optional<PowerUp> powerUpOption = PowerUp.getPowerUpByName(chatMatcher.group(2));
                    int duration = -1;
                    String durationRaw = chatMatcher.group(3);

                    if (!powerUpOption.isPresent()) {
                        NotEnoughZombies.LOGGER.warn("Power up not found for player={}, powerUp={}, duration={}", player, chatMatcher.group(2), duration);
                        return;
                    }

                    if (Objects.isNull(durationRaw)) {
                        return;
                    }
                    duration = Integer.parseInt(durationRaw);

                    bus.post(new PowerUpEvent(player, powerUpOption.get(), duration));
                    break;
                }
                case "foundInLChest": {
                    String player = chatMatcher.group(1);
                    String item = chatMatcher.group(2);
                    bus.post(new LuckyChestEvent(player, item));
                    break;
                }
                case "playerLeft": {
                    String player = chatMatcher.group(1);
                    bus.post(new PlayerConnectionStatusEvent(player, PlayerConnectionStatus.LEFT));
                    break;
                }
                case "playerRejoined": {
                    String player = chatMatcher.group(1);
                    bus.post(new PlayerConnectionStatusEvent(player, PlayerConnectionStatus.REJOINED));
                    break;
                }
                case "openArea": {
                    String player = chatMatcher.group(1);
                    String location = chatMatcher.group(2);
                    bus.post(new PlayerConnectionStatusEvent.OpenAreaEvent(player, location));
                    break;
                }
                case "itemPurchase": {
                    String item = chatMatcher.group(1);
                    bus.post(new ItemPurchaseEvent(item));
                    break;
                }
                case "hitTarget": {
                    String player = chatMatcher.group(1);
                    bus.post(new LuckyChestEvent.HitTargetEvent(player));
                    break;
                }
                case "receiveGold": {
                    int amount = Integer.parseInt(chatMatcher.group(1));
                    bus.post(new GoldReceiveEvent(event, amount));
                    break;
                }
                case "startRepairing": {
                    bus.post(new WindowRepairEvent(event, WindowRepair.START));
                    break;
                }
                case "stopRepairing": {
                    bus.post(new WindowRepairEvent(event, WindowRepair.STOP));
                    break;
                }
                case "finishRepairing": {
                    bus.post(new WindowRepairEvent(event, WindowRepair.FINISH));
                    break;
                }
                case "enemyNearby": {
                    bus.post(new WindowRepairEvent(event, WindowRepair.ENEMY_NEARBY));
                    break;
                }

            }
        }


        if (event.type == 1) {
            NotEnoughZombies.LOGGER.info("{" + event.type + "} " + event.message.getFormattedText());
        }
    }
}
