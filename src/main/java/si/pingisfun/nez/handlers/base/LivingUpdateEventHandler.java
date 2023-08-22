package si.pingisfun.nez.handlers.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.events.entity.PowerUpDespawnEvent;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;
import si.pingisfun.nez.utils.JavaUtils;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LivingUpdateEventHandler {
    private static final  Set<String> IGNORE_ENTITY_SET = new HashSet<>(Arrays.asList("Armor Stand", "§c§lTarget Practice", "§e§lHOLD SNEAK TO REVIVE!", "§e■■■■■■■■■■■■■■■"));
    private static final  Logger RENDER_LOGGER = LogManager.getLogger("RenderLivingEventHandler");
    private static final Pattern REVIVE_SECONDS_PATTERN = Pattern.compile("§c\\d+\\.\\d+s");
    private static final  int MAX_CACHE_SIZE = 200;
    Map<UUID, String> entityNameCache = new LinkedHashMap<UUID, String>() {
        @Override
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };
    private static final String[][] ARMOR_STAND_REGEX_MATRIX = {
            {"powerUpEntity", "(?:§.)*(SHOPPING SPREE|DOUBLE GOLD|MAX AMMO|INSTA KILL|CARPENTER|BONUS GOLD)"},
    };

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!ModConfig.entityDebug || !ZombiesUtils.isZombiesGame()) {
            return;
        }
        EntityLivingBase entity = livingUpdateEvent.entityLiving;
        if (!(entity instanceof EntityArmorStand || entity instanceof EntityZombie)) {
            return;
        }

        String name = entity.getName();
        UUID uuid = entity.getUniqueID();

        if (IGNORE_ENTITY_SET.contains(name)) {
            return;
        }
        // Keep my sanity while debuging
        if (REVIVE_SECONDS_PATTERN.matcher(name).matches()) {
            return;
        }
        String oldName = entityNameCache.get(uuid);
        if (name.equals(oldName)) {
            return;
        }
        entityNameCache.put(uuid, name);
        if (entity instanceof EntityArmorStand) {
            handleArmorStandEntity(entity, name);
        } else {
            if (ModConfig.test != 3) {
                return;
            }
            RENDER_LOGGER.info(name);
        }


    }

    private void handleArmorStandEntity(EntityLivingBase entity, String name) {
        Map.Entry<String, List<Matcher>> matrixRes = JavaUtils.matchRegexMatrix(ARMOR_STAND_REGEX_MATRIX, name);
        if (Objects.isNull(matrixRes)) {
            return;
        }

        String type = matrixRes.getKey();
        Matcher nameMatcher = matrixRes.getValue().get(0);

        EventBus bus = MinecraftForge.EVENT_BUS;
        if (type.equals("powerUpEntity")) {
            String powerUpRaw = WordUtils.capitalize(nameMatcher.group(1).toLowerCase());
            PowerUp powerUp = PowerUp.getPowerUpByName(powerUpRaw);
            if (name.startsWith("§f")) {
                bus.post(new PowerUpDespawnEvent(entity, powerUp));
            } else {
                bus.post(new PowerUpSpawnEvent(entity, powerUp));
            }
        }
    }
}
