package si.pingisfun.nez.utils;

import java.util.Arrays;

public class PowerUpUtils {
    static final int[][] MAX_AMMO = {
            {2, 5, 8, 12, 16}, {1, 6},
            {3, 6, 9, 13, 17}, {2, 7}
    };
    static final int[][] INSTA_KILL = {
            {2, 5, 8, 11, 14, 17, 20, 23}, {},
            {3, 6, 9, 12, 15, 18, 21}, {}
    };
    static final int[][] SHOPPING_SPREE = {
            {5, 15, 45}, {5},
            {6}, {6},
            {7}, {7}
    };

    public static int getPatternNumber(int round, int[][] patternData) {
        for (int i = 0; i < patternData.length; i+=2) {
            int[] pattern = patternData[i];
            if (Arrays.binarySearch(pattern, round) >= 0) {
                return i;
            }
        }
        return -1;
    }
    public static int getNextPowerUpRound(int currentRound, int[][] patternData, int patternNum) {
        int[] pattern = patternData[patternNum];
        int[] digits = patternData[patternNum+1];
        int biggestPatternNum = pattern[pattern.length-1];
        if (biggestPatternNum >= currentRound) {
            return findNumberAfter(pattern, currentRound);
        }
        int tensDown = currentRound-currentRound%10;
        int res = -1;
        int c = 0;
        while (res < currentRound) {
            for (int digit : digits) {
                res = tensDown + digit;
                if (res>= currentRound) {
                    return res;
                }
            }
            tensDown += 10;
            if (c > 100) {
                String patternDataString = Arrays.toString(Arrays.stream(patternData).map(Arrays::toString).toArray());
                throw new StackOverflowError("getNextPowerUpRound() was recursing to infinity. Data: currentRound = " + currentRound + " patternData = " + patternDataString + " patternNum = " + patternNum);
            }
            c++;
            System.out.println(c);
        }
        return res;
    }
    public static int findNumberAfter(int[] numbers, int target) {
        for (int number : numbers) {
            if (number >= target) {
                return number;
            }
        }
        return -1; // If no number is found
    }
}
