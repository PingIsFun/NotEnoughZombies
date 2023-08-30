package si.pingisfun.nez.handlers.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PowerUpCountdown {
    private static final String NAME_SEPARATOR = "\uD83C\uDF82";// "🎂";
    private static final int MAX_CACHE_SIZE = 200;
    private static final int POWER_UP_DESPAWN_SECONDS = 60;
    Map<UUID, Long> entityDespawnTimestampCache = new LinkedHashMap<UUID, Long>() {
        @Override
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    private static String diffToString(long diff) {
        double seconds = diff / 1000.0; // Convert milliseconds to seconds
        int secondsInt = (int) (seconds * 10); // Multiply by 10 to keep one decimal place
        double formattedSeconds = (double) secondsInt / 10; // Divide by 10 to get back to one decimal place

        return formattedSeconds + "s";
    }

    private static String nameGenerator(EntityLivingBase entity, long diff) {
        String newName;

        String suffix = NAME_SEPARATOR + " - " + diffToString(diff);
        String name = entity.getName();

        if (name.contains(NAME_SEPARATOR)) {
            String originalName = name.split(NAME_SEPARATOR)[0];
            newName = originalName + suffix;
        } else {
            newName = name + suffix;
        }
        return newName;
    }

    @SubscribeEvent
    public void onRenderLivingEvent(RenderLivingEvent.Pre renderLivingEvent) {
        if (!ModConfig.powerupCountdown) {
            return;
        }
        EntityLivingBase entity = renderLivingEvent.entity;
        if (!(entity instanceof EntityArmorStand)) {
            return;
        }

        UUID uuid = entity.getUniqueID();
        Long entityDespawnTimestamp = entityDespawnTimestampCache.get(uuid);
        if (Objects.isNull(entityDespawnTimestamp)) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime % 10 != 0) {
            return;
        }

        long diff = entityDespawnTimestamp - currentTime;

        if (diff <= 0) {
            entity.setAlwaysRenderNameTag(false);
            return;
        }

        String newName = nameGenerator(entity, diff);

        entity.setCustomNameTag(newName);
    }

    @SubscribeEvent
    public void onPowerUpSpawnEvent(PowerUpSpawnEvent powerUpSpawnEvent) {
        EntityLivingBase entity = powerUpSpawnEvent.getEntity();
        UUID uuid = entity.getUniqueID();

        Long entityDespawnTimestamp = entityDespawnTimestampCache.get(uuid);
        if (Objects.isNull(entityDespawnTimestamp)) {
            entityDespawnTimestampCache.put(entity.getUniqueID(), System.currentTimeMillis() + POWER_UP_DESPAWN_SECONDS * 1000);
        }

    }
}
