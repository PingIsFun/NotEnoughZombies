package si.pingisfun.nez.events.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Event;
import si.pingisfun.nez.enums.PowerUp;

public abstract class PowerUpEvent extends Event {
    private final EntityLivingBase entity;
    private final PowerUp powerUp;

    protected PowerUpEvent(EntityLivingBase entity, PowerUp powerUp) {
        this.entity = entity;
        this.powerUp = powerUp;
    }

    public EntityLivingBase getEntity() {
        return entity;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
