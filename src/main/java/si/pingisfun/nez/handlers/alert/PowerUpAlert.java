package si.pingisfun.nez.handlers.alert;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.events.entity.PowerUpDespawnEvent;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;
import si.pingisfun.nez.utils.ChatUtil;

import java.util.HashSet;
import java.util.UUID;

public class PowerUpAlert {
    HashSet<UUID> despawnUUIDs = new HashSet<>();
    HashSet<UUID> spawnUUIDs = new HashSet<>();

    @SubscribeEvent
    public void onPowerUpDespawn(PowerUpDespawnEvent powerUpDespawnEvent) {
        UUID uuid = powerUpDespawnEvent.getEntity().getUniqueID();
        if (despawnUUIDs.contains(uuid)) {
            return;
        }
        despawnUUIDs.add(uuid);
        String powerUpName = powerUpDespawnEvent.getPowerUp().getName();
        ChatUtil.printMessage(powerUpName + " is going to despawn soon");
    }

    @SubscribeEvent
    public void onPowerUpSpawn(PowerUpSpawnEvent powerUpSpawnEvent) {
        UUID uuid = powerUpSpawnEvent.getEntity().getUniqueID();
        if (spawnUUIDs.contains(uuid)) {
            return;
        }
        spawnUUIDs.add(uuid);
        String powerUpName = powerUpSpawnEvent.getPowerUp().getName();
        ChatUtil.printMessage(powerUpName + " spawned");
    }
}
