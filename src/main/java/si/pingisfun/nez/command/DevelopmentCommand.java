package si.pingisfun.nez.command;

import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.events.player.PowerUpPickupEvent;
import si.pingisfun.nez.utils.ChatUtil;

import java.util.Locale;
import java.util.Optional;

@Command(value = "nezdev", description = "Development command.")

public class DevelopmentCommand {
    private final ClientChatReceivedEvent emptyChatEvent = new ClientChatReceivedEvent((byte) 1, new UTextComponent("Test"));

    @Main
    private void handle() {
        NotEnoughZombies.config.openGui();
    }

    @SubCommand()
    private void activatePowerUpPickupEvent(int timeLeft, String powerUpName) {
        Optional<PowerUp> powerUpOptional = PowerUp.getPowerUpByShortName(powerUpName.toUpperCase(Locale.ROOT));

        if (!powerUpOptional.isPresent()) {
            ChatUtil.printError("Invalid power up name");
            return;
        }
        PowerUp powerUp = powerUpOptional.get();

        NotEnoughZombies.LOGGER.info(powerUp.toString());
        MinecraftForge.EVENT_BUS.post(new PowerUpPickupEvent(emptyChatEvent, "Player", powerUp, timeLeft));
    }
}
