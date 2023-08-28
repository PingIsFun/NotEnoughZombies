package si.pingisfun.nez.handlers.game;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.enums.PowerUp;
import si.pingisfun.nez.enums.ZombiesMap;
import si.pingisfun.nez.events.entity.PowerUpSpawnEvent;
import si.pingisfun.nez.events.game.GameOverEvent;
import si.pingisfun.nez.events.game.GameStartEvent;
import si.pingisfun.nez.events.game.NewRoundEvent;
import si.pingisfun.nez.events.player.PowerUpPickupEvent;
import si.pingisfun.nez.utils.ChatUtil;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.*;

public class ZombiesGame {
    private ZombiesMap map;
    private Integer currentRound;
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

    public Optional<Integer> getNextPowerUpRound(PowerUp powerUp) {
        Optional<Integer> patternNum = getPatternNum(powerUp);
        if (!patternNum.isPresent()) {
            return Optional.empty();
        }

        return powerUp.getNextPowerUpRound(currentRound, patternNum.get());
    }

    private Optional<Integer> getPatternNum(PowerUp powerUp) {
        if (!powerUp.hasPattern()) {
            return Optional.empty();
        }
        Integer powerUpPattern = powerUpPatternMap.get(powerUp);

        if (Objects.isNull(powerUpPattern)) {
            return Optional.empty();
        }

        return Optional.of(powerUpPattern);

    }

    private boolean powerUpPatternExists(PowerUp powerUp) {
        return Objects.nonNull(powerUpPatternMap.get(powerUp));
    }

    @SubscribeEvent
    public void onNewRound(NewRoundEvent newRoundEvent) {
        if (Objects.isNull(map)) {
            this.map = ZombiesUtils.getMap();
        }
        this.currentRound = newRoundEvent.getRound();
        int ss = getNextPowerUpRound(PowerUp.SHOPPING_SPREE).orElse(-1);
        int ik = getNextPowerUpRound(PowerUp.INSTA_KILL).orElse(-1);
        int mx = getNextPowerUpRound(PowerUp.MAX_AMMO).orElse(-1);
        ChatUtil.printMessage("New Round: " + this.currentRound + ". SS: " + ss + " IK: " + ik + " MX: " + mx);
    }

    @SubscribeEvent
    public void onPowerUpSpawn(PowerUpSpawnEvent powerUpSpawnEvent) {
        PowerUp powerUp = powerUpSpawnEvent.getPowerUp();
        NotEnoughZombies.LOGGER.info("POWERUP: " + powerUp);

        if (!powerUp.hasPattern()) {
            return;
        }

        if (powerUpPatternExists(powerUp)) {
            return;
        }

        Optional<Integer> patternNum = powerUp.getPatternNumber(this.currentRound);

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
        NotEnoughZombies.LOGGER.info("PowerUp Pickup event triggered for {}", powerUpPickupEvent.getPowerUp().getName());

        if (!powerUp.hasPattern()) {
            return;
        }

        if (powerUpPatternExists(powerUp)) {
            return;
        }

        Optional<Integer> patternNum = powerUp.getPatternNumber(this.currentRound);

        if (!patternNum.isPresent()) {
            NotEnoughZombies.LOGGER.warn("PowerUp pattern not found. PowerUp: " + powerUp.getName() + " round: " + this.currentRound);
            return;
        }
        powerUpPatternMap.put(powerUp, patternNum.get());
    }

    @SubscribeEvent
    public void onGameOver(GameOverEvent gameOverEvent) {
        clean();
    }

    private void clean() {
        isInGame = false;
        map = null;
        currentRound = null;
        powerUpPatternMap = new HashMap<>(3);
    }

    public void resetPowerUpPattern(PowerUp powerUp) {
        if (!isInGame) {
            return;
        }
        powerUpPatternMap.remove(powerUp);
        ChatUtil.printMessage(powerUp.getName() + " was reset.");
    }

    public void setPowerUpPattern(PowerUp powerUp, Integer pattern) {
        if (!isInGame) {
            return;
        }

        Optional<List<SortedSet<Integer>>> patternOptional = powerUp.getPattern();
        if (!patternOptional.isPresent()) {
            return;
        }

        int numberOfPatterns = patternOptional.get().size();

        if (!(pattern >= 0 && pattern < numberOfPatterns)) {
            return;
        }

        powerUpPatternMap.put(powerUp, pattern);
        ChatUtil.printMessage(powerUp.getName() + " pattern set to " + pattern);
    }
}
