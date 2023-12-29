package si.pingisfun.nez.enums;


import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import si.pingisfun.nez.data.PowerUpPatternData;

import java.util.*;

public enum PowerUp {
    INSTA_KILL("Insta Kill", "IK", ChatColor.RED,
            PowerUpPatternData.AlienArcadium.INSTA_KILL,
            PowerUpPatternData.BadBlood.INSTA_KILL,
            PowerUpPatternData.DeadEnd.INSTA_KILL
    ),
    MAX_AMMO("Max Ammo", "MX", ChatColor.BLUE,
            PowerUpPatternData.AlienArcadium.MAX_AMMO,
            PowerUpPatternData.BadBlood.MAX_AMMO,
            PowerUpPatternData.DeadEnd.MAX_AMMO),
    SHOPPING_SPREE("Shopping Spree", "SS", ChatColor.DARK_PURPLE,
            PowerUpPatternData.AlienArcadium.SHOPPING_SPREE,
            null,
            null),
    DOUBLE_GOLD("Double Gold", "DG", ChatColor.GOLD),
    CARPENTER("Carpenter", "CA", ChatColor.BLUE),
    BONUS_GOLD("Bonus Gold", "BG", ChatColor.GOLD);

    private final String name;

    private final String shortName;
    private final ChatColor color;
    private EnumMap<ZombiesMap, Optional<List<SortedSet<Integer>>>> patternMap = new EnumMap<>(ZombiesMap.class);
    PowerUp(String name, String shortName, ChatColor color, List<SortedSet<Integer>> AApattern, List<SortedSet<Integer>> BBpattern, List<SortedSet<Integer>> DEpattern) {
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.patternMap = new EnumMap<>(ZombiesMap.class);
        patternMap.put(ZombiesMap.ALIEN_ARCADIUM, Optional.ofNullable(AApattern));
        patternMap.put(ZombiesMap.BAD_BLOOD, Optional.ofNullable(BBpattern));
        patternMap.put(ZombiesMap.DEAD_END, Optional.ofNullable(DEpattern));
    }

    PowerUp(String name, String shortName, ChatColor color) {
        this.name = name;
        this.shortName = shortName;
        this.color = color;
    }

    public static Optional<PowerUp> getPowerUpByName(String name) {
        for (PowerUp powerUp : PowerUp.values()) {
            if (powerUp.name.equalsIgnoreCase(name)) {
                return Optional.of(powerUp);
            }
        }
        return Optional.empty();
    }

    public static Optional<PowerUp> getPowerUpByShortName(String shortName) {
        for (PowerUp powerUp : PowerUp.values()) {
            if (powerUp.shortName.equalsIgnoreCase(shortName)) {
                return Optional.of(powerUp);
            }
        }
        return Optional.empty();
    }

    public ChatColor getColor() {
        return color;
    }

    public String getShortName() {
        return shortName;
    }

    public Optional<List<SortedSet<Integer>>> getPattern(ZombiesMap map) {
        Optional<List<SortedSet<Integer>>> p = patternMap.get(map);

        if (Objects.isNull(p)) {
            return Optional.empty();
        }

        return p;
    }

    public boolean hasPattern(ZombiesMap map) {
        return getPattern(map).isPresent();
    }

    public String getName() {
        return name;
    }

    public Optional<Integer> getPatternNumber(ZombiesMap map, int round) {
        Optional<List<SortedSet<Integer>>> patternDataOption = this.getPattern(map);
        if (!patternDataOption.isPresent()) {
            return Optional.empty();
        }

        List<SortedSet<Integer>> patternData = patternDataOption.get();
        for (int i = 0; i < patternData.size(); i += 2) {
            SortedSet<Integer> powerUpPattern = patternData.get(i);
            if (powerUpPattern.contains(round)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> getNextPowerUpRound(ZombiesMap map, int currentRound, int patternNum) {
        if (patternNum % 2 == 1) {
            return Optional.empty();
        }

        Optional<List<SortedSet<Integer>>> patternDataOption = this.getPattern(map);
        if (!patternDataOption.isPresent()) {
            return Optional.empty();
        }

        List<SortedSet<Integer>> patternData = patternDataOption.get();

        if (patternData.size() < patternNum + 1) {
            return Optional.empty();
        }
        SortedSet<Integer> powerupPattern = patternData.get(patternNum);
        SortedSet<Integer> digits = patternData.get(patternNum + 1);
        int biggestPatternNum = Collections.max(powerupPattern);
        if (biggestPatternNum >= currentRound) {
            return findNumberAfter(powerupPattern, currentRound);
        }
        if (digits.isEmpty()) {
            return Optional.empty();
        }
        int tensDown = currentRound - currentRound % 10;
        int res = -1;
        for (int i = 0; i < 10; i++) {
            for (int digit : digits) {
                res = tensDown + digit;
                if (res >= currentRound) {
                    return Optional.of(res);
                }
            }
            tensDown += 10;
        }
        return Optional.empty();
    }

    private Optional<Integer> findNumberAfter(SortedSet<Integer> numbers, int target) {
        for (int number : numbers) {
            if (number >= target) {
                return Optional.of(number);
            }
        }
        return Optional.empty(); // If no number is found
    }

    @Override
    public String toString() {
        return "PowerUp{" +
                "name='" + name + '\'' +
                ", pattern=" + patternMap +
                '}';
    }
}