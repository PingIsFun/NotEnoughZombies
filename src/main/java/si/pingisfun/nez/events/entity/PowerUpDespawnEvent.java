package si.pingisfun.nez.events.entity;

import net.minecraft.entity.EntityLivingBase;
import si.pingisfun.nez.enums.PowerUp;

public class PowerUpDespawnEvent extends PowerUpEvent {

    public PowerUpDespawnEvent(EntityLivingBase entity, PowerUp powerUp) {
        super(entity, powerUp);
    }
}
