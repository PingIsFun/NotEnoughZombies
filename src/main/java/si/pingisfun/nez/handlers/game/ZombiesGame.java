package si.pingisfun.nez.handlers.game;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.config.ModConfig;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.enums.ZombiesMap;
import si.pingisfun.nez.enums.config.ChatOutput;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;
import si.pingisfun.nez.events.game.GameOverEvent;
import si.pingisfun.nez.events.game.GameStartEvent;
import si.pingisfun.nez.events.game.NewRoundEvent;
import si.pingisfun.nez.events.player.LuckyChestEvent;
import si.pingisfun.nez.events.player.PowerUpPickupEvent;
import si.pingisfun.nez.utils.ChatUtil;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.*;

public class ZombiesGame {
    private Map<String, Map<String, Integer>> luckyChestRollData = new HashMap<>(4);
    private ZombiesMap map;
    private int currentRound;
    private Map<PowerUp, Integer> powerUpPatternMap = new HashMap<>(3);
    private boolean isInGame;

    public ZombiesGame() {
        this.isInGame = false;
    }

    @SubscribeEvent
    public void onGameStart(GameStartEvent gameStartEvent) {
        clean();
        map = ZombiesUtils.getMap();
        currentRound = 1;
        isInGame = true;
    }

    @SubscribeEvent
    public void onNewRound(NewRoundEvent newRoundEvent) {
        if (Objects.isNull(map)) {
            this.map = ZombiesUtils.getMap();
        }
        this.currentRound = newRoundEvent.getRound();
        StringBuilder powerUpString = new StringBuilder();
        Set<PowerUp> allSavedPowerUps = getAllSavedPowerUps();
        if (allSavedPowerUps.size() == 0) {
            ChatUtil.message("No power up data");
            return;
        }
        int c = 0;
        for (PowerUp powerUp : allSavedPowerUps) {
            int nextRoundWithPowerUp = getNextPowerUpRound(powerUp).orElse(-1);
            powerUpString
                    .append(powerUp.getShortName())
                    .append(": ")
                    .append(nextRoundWithPowerUp);
            if (c >= allSavedPowerUps.size() - 1) {
                break;
            }

            powerUpString.append(", ");

            c++;
        }


        ChatUtil.message("Next power up rounds; " + powerUpString, ChatOutput.getOutputUpByNumber(ModConfig.nextPowerUpRoundAlert));
    }

    @SubscribeEvent
    public void onPowerUpSpawn(PowerUpSpawnEvent powerUpSpawnEvent) {
        PowerUp powerUp = powerUpSpawnEvent.getPowerUp();

        if (!powerUp.hasPattern(this.map)) {
            return;
        }

        if (powerUpPatternExists(powerUp)) {
            return;
        }

        Optional<Integer> patternNum = powerUp.getPatternNumber(this.map, this.currentRound);

        if (!patternNum.isPresent()) {
            NotEnoughZombies.LOGGER.warn("PowerUp pattern not found. PowerUp: " + powerUp.getName() + " round: " + this.currentRound);
            return;
        }
        powerUpPatternMap.put(powerUp, patternNum.get());
    }

    @SubscribeEvent
    public void onPowerUpPickup(PowerUpPickupEvent powerUpPickupEvent) {
        // Fix for instant pickup of powerups
        PowerUp powerUp = powerUpPickupEvent.getPowerUp();

        if (!powerUp.hasPattern(this.map)) {
            return;
        }

        if (powerUpPatternExists(powerUp)) {
            return;
        }

        Optional<Integer> patternNum = powerUp.getPatternNumber(this.map, this.currentRound);

        if (!patternNum.isPresent()) {
            NotEnoughZombies.LOGGER.warn("PowerUp pattern not found. PowerUp: " + powerUp.getName() + " round: " + this.currentRound);
            return;
        }
        powerUpPatternMap.put(powerUp, patternNum.get());
    }

    @SubscribeEvent
    public void onLuckyChestEvent(LuckyChestEvent luckyChestEvent) {
        String player = luckyChestEvent.getPlayer();
        String item = luckyChestEvent.getItem();

        luckyChestRollData.putIfAbsent(player, new HashMap<>());
        Map<String, Integer> playerItems = luckyChestRollData.get(player);

        playerItems.putIfAbsent(item, 0);
        playerItems.put(item, playerItems.get(item) + 1);
    }


    @SubscribeEvent
    public void onGameOver(GameOverEvent gameOverEvent) {
        clean();
    }

    private void clean() {
        isInGame = false;
        map = null;
        currentRound = -1;
        powerUpPatternMap = new HashMap<>(3);
        luckyChestRollData = new HashMap<>(4);
    }

    public Optional<Integer> getNextPowerUpRound(PowerUp powerUp) {
        Optional<Integer> patternNum = getPatternNum(powerUp);
        if (!patternNum.isPresent()) {
            return Optional.empty();
        }

        return powerUp.getNextPowerUpRound(this.map, currentRound, patternNum.get());
    }

    private Optional<Integer> getPatternNum(PowerUp powerUp) {
        if (!powerUp.hasPattern(this.map)) {
            return Optional.empty();
        }
        Integer powerUpPattern = powerUpPatternMap.get(powerUp);

        if (Objects.isNull(powerUpPattern)) {
            return Optional.empty();
        }

        return Optional.of(powerUpPattern);

    }

    private Set<PowerUp> getAllSavedPowerUps() {
        return powerUpPatternMap.keySet();
    }

    private boolean powerUpPatternExists(PowerUp powerUp) {
        return Objects.nonNull(powerUpPatternMap.get(powerUp));
    }

    public void resetPowerUpPattern(PowerUp powerUp) {
        if (!isInGame) {
            return;
        }
        powerUpPatternMap.remove(powerUp);
        ChatUtil.message(powerUp.getName() + " was reset.");
    }

    public void setPowerUpPattern(PowerUp powerUp, Integer pattern) {
        if (!isInGame) {
            return;
        }

        Optional<List<SortedSet<Integer>>> patternOptional = powerUp.getPattern(this.map);
        if (!patternOptional.isPresent()) {
            return;
        }

        int numberOfPatterns = patternOptional.get().size();

        if (!(pattern >= 0 && pattern < numberOfPatterns)) {
            return;
        }

        powerUpPatternMap.put(powerUp, pattern);
        ChatUtil.message(powerUp.getName() + " pattern set to " + pattern);
    }

    public Map<String, Map<String, Integer>> getLuckyChestRollData() {
        return luckyChestRollData;
    }

    @Override
    public String toString() {
        return "ZombiesGame{" +
                "luckyChestRollData=" + luckyChestRollData +
                ", map=" + map +
                ", currentRound=" + currentRound +
                ", powerUpPatternMap=" + powerUpPatternMap +
                ", isInGame=" + isInGame +
                '}';
    }
}
