package si.pingisfun.nez.handlers.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.utils.ZombiesUtils;

public class EntityJoinWorldHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent entityJoinWorldEvent) {
        if (!ZombiesUtils.isEnabled()) {
            return;
        }

        Entity entity = entityJoinWorldEvent.entity;
        if (!(entity instanceof EntityGiantZombie)) {
        }
//        ChatUtil.sendChatMessage("Gian Zombie Spawned");
    }
}
