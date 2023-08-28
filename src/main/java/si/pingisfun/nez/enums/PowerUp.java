package si.pingisfun.nez.enums;


import java.util.*;
import java.util.stream.Collectors;

public enum PowerUp {
    INSTA_KILL("Insta Kill", new Pattern(ZombiesMap.ALIEN_ARCADIUM, new int[][]{
            {2, 5, 8, 11, 14, 17, 20, 23}, {},
            {3, 6, 9, 12, 15, 18, 21}, {}
    })),
    MAX_AMMO("Max Ammo", new Pattern(ZombiesMap.ALIEN_ARCADIUM, new int[][]{
            {2, 5, 8, 12, 16}, {1, 6},
            {3, 6, 9, 13, 17}, {2, 7}
    })),
    SHOPPING_SPREE("Shopping Spree", new Pattern(() -> {
        HashMap<ZombiesMap, List<SortedSet<Integer>>> r = new HashMap<>(1);
        r.put(ZombiesMap.ALIEN_ARCADIUM, Pattern.intConverter(new int[][]{
                {5, 15, 45}, {5},
                {6}, {6},
                {7}, {7}
        }));
        return r;
    }
    )),
    DOUBLE_GOLD("Double Gold"),
    CARPENTER("Carpenter"),
    BONUS_GOLD("Bonus Gold");

    private final String name;
    public static final Set<PowerUp> PATTERN_POWERUPS = new HashSet<>(Arrays.asList(INSTA_KILL, MAX_AMMO, SHOPPING_SPREE));

    private Optional<Pattern> pattern = Optional.empty();

    PowerUp(String name, Pattern pattern) {
        this.name = name;
        this.pattern = Optional.of(pattern);
    }

    PowerUp(String name) {
        this.name = name;
    }

    public Optional<Pattern> getPattern() {
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

    public Optional<Integer> getPatternNumber(int round) {
        Optional<Pattern> patternOption = this.getPattern();
        if (!patternOption.isPresent()) {
            return Optional.empty();
        }

        List<SortedSet<Integer>> patternData = patternOption.get().getPatternData();

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

        Optional<Pattern> patternOptional = this.getPattern();
        if (!patternOptional.isPresent()) {
            return Optional.empty();
        }

        List<SortedSet<Integer>> patternData = patternOptional.get().getPatternData();

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

    public static class Pattern {
        ZombiesMap map;
        List<SortedSet<Integer>> patternData;

        public Pattern(Map<ZombiesMap, List<SortedSet<Integer>>> data) {

            this.map = map;
            this.patternData = res;
        }
        public static List<SortedSet<Integer>> intConverter(int[][] data) {
            List<SortedSet<Integer>> res = new ArrayList<>();

            for (int[] arr : data) {
                res.add(new TreeSet<>(Arrays.stream(arr).boxed().collect(Collectors.toList())));
            }
            return res;
        }

        public ZombiesMap getMap() {
            return map;
        }

        public List<SortedSet<Integer>> getPatternData() {
            return patternData;
        }

        @Override
        public String toString() {
            return "Pattern{" +
                    "map=" + map +
                    ", patternData=" + patternData +
                    '}';
        }
    }
}