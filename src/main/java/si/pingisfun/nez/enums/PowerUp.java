package si.pingisfun.nez.enums;


import java.util.*;

public enum PowerUp {
    INSTA_KILL("Insta Kill", Arrays.asList(
            new TreeSet<>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23)), Collections.emptySortedSet(),
            new TreeSet<>(Arrays.asList(3, 6, 9, 12, 15, 18, 21)), Collections.emptySortedSet()
    )),
    MAX_AMMO("Max Ammo", Arrays.asList(
            new TreeSet<>(Arrays.asList(2, 5, 8, 12, 16)), new TreeSet<>(Arrays.asList(1, 6)),
            new TreeSet<>(Arrays.asList(3, 6, 9, 13, 17)), new TreeSet<>(Arrays.asList(2, 7))
    )),
    SHOPPING_SPREE("Shopping Spree", Arrays.asList(
            new TreeSet<>(Arrays.asList(5, 15, 45)), new TreeSet<>(Collections.singleton(5)),
            new TreeSet<>(Collections.singleton(6)), new TreeSet<>(Collections.singleton(6)),
            new TreeSet<>(Collections.singleton(7)), new TreeSet<>(Collections.singleton(7))
    )),
    DOUBLE_GOLD("Double Gold"),
    CARPENTER("Carpenter"),
    BONUS_GOLD("Bonus Gold");

    private final String name;
    public static final Set<PowerUp> PATTERN_POWERUPS = new HashSet<>(Arrays.asList(INSTA_KILL, MAX_AMMO, SHOPPING_SPREE));

    private Optional<List<SortedSet<Integer>>> pattern = Optional.empty();

    PowerUp(String name, List<SortedSet<Integer>> pattern) {
        this.name = name;
        this.pattern = Optional.of(pattern);
    }

    PowerUp(String name) {
        this.name = name;
    }

    public Optional<List<SortedSet<Integer>>> getPattern() {
        return pattern;
    }

    public boolean hasPattern() {
        return Objects.nonNull(this.pattern);
    }

    public static Optional<PowerUp> getPowerUpByName(String name) {
        for (PowerUp powerUp : PowerUp.values()) {
            if (powerUp.name.equalsIgnoreCase(name)) {
                return Optional.of(powerUp);
            }
        }
        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    public Optional<Integer> getPatternNumber(Integer round) {
        if (Objects.isNull(round)) {
            return Optional.empty();
        }
        Optional<List<SortedSet<Integer>>> patternDataOption = this.getPattern();
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

    public Optional<Integer> getNextPowerUpRound(int currentRound, int patternNum) {
        if (patternNum % 2 == 1) {
            return Optional.empty();
        }

        Optional<List<SortedSet<Integer>>> patternDataOption = this.getPattern();
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
                ", pattern=" + pattern +
                '}';
    }
}