package si.pingisfun.nez.handlers.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.utils.ChatUtil;

public class EntityJoinWorldHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent entityJoinWorldEvent) {
        Entity entity = entityJoinWorldEvent.entity;
        if (!(entity instanceof EntityGiantZombie)) {
            return;
        }
//        ChatUtil.sendChatMessage("Gian Zombie Spawned");
    }
}
