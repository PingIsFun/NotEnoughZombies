package si.pingisfun.nez.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.config.ChatOutput;
import si.pingisfun.nez.utils.ChatUtil;

@Command(value = "test")
public class TestCommand {
    @Main
    private void handle() {

        NotEnoughZombies.LOGGER.info("Roll data: " + NotEnoughZombies.game.getLuckyChestRollData());
    }
}
