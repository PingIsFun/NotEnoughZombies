package si.pingisfun.nez.handlers.datacollector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.events.game.NewRoundEvent;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.*;
import java.util.stream.Collectors;

import static si.pingisfun.nez.NotEnoughZombies.MODID;


public class DCHandleLivingEvent {
    public static final Logger DATA_LOGGER = LogManager.getLogger(MODID+"-DATA_COLLECTOR");
    public static final Logger INFO_LOGGER = LogManager.getLogger(MODID+"-DATA_COLLECTOR");

    HashMap<UUID, Long> checkedUUIDs = new HashMap<>();

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!ZombiesUtils.isEnabled() ||!ModConfig.dataCollector) {
            return;
        }
        EntityLivingBase entity = livingUpdateEvent.entityLiving;

        if (entity instanceof EntityArmorStand && ModConfig.hideArmorStands) {
            return;
        }
        if (entity instanceof EntityOtherPlayerMP && ModConfig.hideOtherPlayers) {
            return;
        }
        if (entity instanceof EntityVillager && ModConfig.hideVillagers) {
            return;
        }
        if (entity instanceof EntityPlayerSP && ModConfig.hideSelfPlayer) {
            return;
        }



        long epochSeconds = System.currentTimeMillis() / 1000;

        Long uuidTimestamp = checkedUUIDs.get(entity.getUniqueID());
        if (Objects.nonNull(uuidTimestamp) && (epochSeconds - uuidTimestamp) < ModConfig.dcRecheckDelay) {
            return;
        }
        Map<String, Object> infoMap = new HashMap<>();
        checkedUUIDs.put(entity.getUniqueID(), epochSeconds);

        // Now you have a map containing the information in the desired format
        infoMap.put("uuid", entity.getUniqueID());
        infoMap.put("health", String.valueOf(entity.getHealth()));
        infoMap.put("moveSpeed", String.valueOf(entity.getAIMoveSpeed()));

        infoMap.put("potinEffects", entity.getActivePotionEffects().stream().map(effect ->
                String.format("Effect{" +
                                "getEffectName=%s," +
                                "getDuration=%s," +
                                "getAmplifier=%s," +
                                "getIsAmbient=%s," +
                                "getIsShowParticles=%s," +
                                "getCurativeItems=%s," +
                                "getIsPotionDurationMax=%s," +
                                "getPotionID=%s}",
                effect.getEffectName(),
                effect.getDuration(),
                effect.getAmplifier(),
                effect.getIsAmbient(),
                effect.getIsShowParticles(),
                effect.getCurativeItems(),
                effect.getIsPotionDurationMax(),
                effect.getPotionID())).collect(Collectors.joining(",")));

        infoMap.put("name", entity.getName());
        infoMap.put("customNameTag", entity.getCustomNameTag());
        infoMap.put("displayName", entity.getDisplayName().toString());
        infoMap.put("creatureAttributes", String.valueOf(entity.getCreatureAttribute()));

        infoMap.put("attributes", entity.getAttributeMap().getAllAttributes().stream().map(attribute -> {
                Map<String, Object> attributeMap = new HashMap<>();
                attributeMap.put("getAttributeUnlocalizedName", attribute.getAttribute().getAttributeUnlocalizedName());
                attributeMap.put("getAttributeValue", String.valueOf(attribute.getAttributeValue()));
                attributeMap.put("getBaseValue", String.valueOf(attribute.getBaseValue()));
                attributeMap.put("modifiers", attribute.func_111122_c().stream().map(attributeModifier -> {
                    Map<String, String> modfierMap = new HashMap<>();
                    modfierMap.put("amount", String.valueOf(attributeModifier.getAmount()));
                    modfierMap.put("operation", String.valueOf(attributeModifier.getOperation()));
                    modfierMap.put("name", attributeModifier.getName());
                    modfierMap.put("id", String.valueOf(attributeModifier.getID()));
                    modfierMap.put("serialize", String.valueOf(attributeModifier.isSaved()));
                    return modfierMap;

                }).collect(Collectors.toSet()));
                return attributeMap;
                }
        ).collect(Collectors.toSet()));

        infoMap.put("pos", entity.getPosition().toString());
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            ItemStack armor = entity.getCurrentArmor(i);
            if (Objects.isNull(armor)) {
                continue;
            }
            sb.append(armor.getDisplayName()).append(",");
            sb.append(armor.getEnchantmentTagList());
            infoMap.put("armor" + i, sb.toString());
        }
        if (Objects.nonNull(entity.getHeldItem())) {
            infoMap.put("hand", "Item="+entity.getHeldItem().getUnlocalizedName() + ",Enchants=" + entity.getHeldItem().getEnchantmentTagList());
        }
        infoMap.put("team", String.valueOf(entity.getTeam()));
        infoMap.put("armorVal", String.valueOf(entity.getTotalArmorValue()));
        infoMap.put("entityData", String.valueOf(entity.getEntityData()));
        infoMap.put("entity", String.valueOf(entity));
        Gson gson = new Gson();
        String json = gson.toJson(infoMap);
        DATA_LOGGER.info(json);
    }
    @SubscribeEvent
    public void onNewRound(NewRoundEvent newRoundEvent) {
        INFO_LOGGER.info("New Round: {}", newRoundEvent.getRound());
    }
}
