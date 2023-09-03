package si.pingisfun.nez.command;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.enums.config.ChatOutput;
import si.pingisfun.nez.utils.ChatUtil;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see NotEnoughZombies
 */
@Command(value = NotEnoughZombies.MODID, description = "Access the " + NotEnoughZombies.NAME + " GUI.")
public class NEZCommand {
    static Map<String, PowerUp> powerupCommandMap = new HashMap<>(3);

    public NEZCommand() {
        powerupCommandMap.put("ik", PowerUp.INSTA_KILL);
        powerupCommandMap.put("mx", PowerUp.MAX_AMMO);
        powerupCommandMap.put("ss", PowerUp.SHOPPING_SPREE);

    }

    @Main
    private void handle() {
        NotEnoughZombies.config.openGui();
    }

    @SubCommand(aliases = "sp")
    private void setPowerUpRound(String powerUpName, int round) {
        PowerUp powerUp = powerupCommandMap.get(powerUpName);

        if (Objects.isNull(powerUp)) {
            ChatUtil.printError("Invalid power up name");
            return;
        }

        if (round == -1) {
            NotEnoughZombies.game.resetPowerUpPattern(powerUp);
            return;
        }

        Optional<Integer> powerUpPatternNumOption = powerUp.getPatternNumber(ZombiesUtils.getMap(), round);
        if (!powerUpPatternNumOption.isPresent()) {
            ChatUtil.printError("Power up pattern not found");
            return;
        }
        NotEnoughZombies.game.setPowerUpPattern(powerUp, powerUpPatternNumOption.get());
    }

    @SubCommand
    private void roll() {
        ChatUtil.sendLuckyChestRollDataToChat();
    }
}