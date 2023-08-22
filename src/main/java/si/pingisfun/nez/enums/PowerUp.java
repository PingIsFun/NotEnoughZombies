package si.pingisfun.nez.enums;


import java.util.Arrays;
import java.util.Objects;

public enum PowerUp {
    INSTA_KILL("Insta Kill", new int[][]{
            {2, 5, 8, 11, 14, 17, 20, 23}, {},
            {3, 6, 9, 12, 15, 18, 21}, {}
    }),
    MAX_AMMO("Max Ammo", new int[][]{
            {2, 5, 8, 12, 16}, {1, 6},
            {3, 6, 9, 13, 17}, {2, 7}
    }),
    SHOPPING_SPREE("Shopping Spree", new int[][]{
            {5, 15, 45}, {5},
            {6}, {6},
            {7}, {7}
    }),
    DOUBLE_GOLD("Double Gold"),
    CARPENTER("Carpenter"),
    BONUS_GOLD("Bonus Gold");

    private final String name;

    private int[][] pattern = null;

    PowerUp(String name, int[][] pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    PowerUp(String name) {
        this.name = name;
    }

    public int[][] getPattern() {
        return pattern;
    }

    public boolean hasPattern() {
        return Objects.nonNull(this.pattern);
    }

    public static PowerUp getPowerUpByName(String name) {
        for (PowerUp powerUp : PowerUp.values()) {
            if (powerUp.name.equalsIgnoreCase(name)) {
                return powerUp;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    private int getPatternNumber(int round) {
        int[][] patternData = this.getPattern();
        for (int i = 0; i < patternData.length; i += 2) {
            int[] pattern = patternData[i];
            if (Arrays.binarySearch(pattern, round) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNextPowerUpRound(int currentRound, int patternNum) {
        int[][] patternData = this.getPattern();
        int[] pattern = patternData[patternNum];
        int[] digits = patternData[patternNum + 1];
        int biggestPatternNum = pattern[pattern.length - 1];
        if (biggestPatternNum >= currentRound) {
            return findNumberAfter(pattern, currentRound);
        }
        int tensDown = currentRound - currentRound % 10;
        int res = -1;
        int c = 0;
        while (res < currentRound) {
            for (int digit : digits) {
                res = tensDown + digit;
                if (res >= currentRound) {
                    return res;
                }
            }
            tensDown += 10;
            if (c > 100) {
                String patternDataString = Arrays.toString(Arrays.stream(patternData).map(Arrays::toString).toArray());
                throw new StackOverflowError("getNextPowerUpRound() was recursing to infinity."
                        + " Data: currentRound = " + currentRound
                        + " patternData = " + patternDataString
                        + " patternNum = " + patternNum);
            }
            c++;
        }
        return res;
    }

    private int findNumberAfter(int[] numbers, int target) {
        for (int number : numbers) {
            if (number >= target) {
                return number;
            }
        }
        return -1; // If no number is found
    }
}