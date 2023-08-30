package si.pingisfun.nez.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;

@Command(value = "test")
public class TestCommand {
    @Main
    private void handle() {

        Minecraft minecraft = Minecraft.getMinecraft();
        NotEnoughZombies.LOGGER.debug("TEST DEBUG");
        NotEnoughZombies.LOGGER.info(minecraft);
        NotEnoughZombies.LOGGER.info(minecraft.pointedEntity);
        MinecraftForge.EVENT_BUS.post(new PowerUpSpawnEvent((EntityLivingBase) minecraft.pointedEntity, PowerUp.INSTA_KILL));
    }
}
