package si.pingisfun.nez.handlers.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.config.ModConfig;

public class TestHandler {
    final static Logger TEST_LOGGER =  LogManager.getLogger("NEZ TEST");
//    @SubscribeEvent
//    public void onPlayedSound(PlaySoundEvent playSoundEvent) {
//        if (ModConfig.test != 2) {
//            return;
//        }
//        NotEnoughZombies.LOGGER.info("Sound Name:" + playSoundEvent.name + " Sound Category: " + playSoundEvent.category.getCategoryName());
//    }

}
