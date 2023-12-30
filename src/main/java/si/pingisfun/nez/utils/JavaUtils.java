package si.pingisfun.nez.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaUtils {
    public static String firstLetterCapitalWithSingleSpace(final String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    /**
     * Matches regular expressions from a matrix against an array of strings and returns a map of match results.
     *
     * @param regexMatrix  A 2D array of regex patterns where each row represents a pattern and its associated type.
     *                     The first element of each row is the type, followed by the regex patterns.
     * @param matchStrings An array of strings to match against the regex patterns in the matrix.
     * @return A map containing the match results. The key is the type from the matrix, and the value is a list of Matchers
     * representing the match results for each string.
     * @throws ArrayIndexOutOfBoundsException If the number of regex patterns in a row of the matrix doesn't match the number of
     *                                        strings to be matched.
     * @throws PatternSyntaxException         If there's an error in the syntax of any regex pattern in the matrix.
     *                                        <p>
     *                                        <p>
     *                                        Example usage:
     *                                        String[][] regexMatrix = {{"newRound", "§cRound (\\d*)§r", "§r"}, {"gameOver", "§cGame Over!§r", "§7You made it to Round (\\d*)!§r"},};
     *                                        String[] matchStrings = {"§cRound 1§r", "§r"};
     *                                        Map<String, List<Matcher>> results = matchRegexMatrix(regexMatrix, matchStrings);
     */
    public static Map.Entry<String, List<Matcher>> matchRegexMatrix(String[][] regexMatrix, String[] matchStrings) {
        Matcher matcher;
        String type;
        List<Matcher> matcherList = new LinkedList<>();
        for (int i = 0; i < regexMatrix.length; i++) {
            matcherList.clear();
            String[] currentRow = regexMatrix[i];
            if (currentRow.length - 1 != matchStrings.length) {
                throw new ArrayIndexOutOfBoundsException("Mismatched dimensions: The number of regex patterns in the matrix does not match the number of strings to be matched.");
            }
            type = currentRow[0];

            for (int j = 0; j < matchStrings.length; j++) {
                String matchString = matchStrings[j];
                Pattern namePattern = Pattern.compile(currentRow[j + 1]);
                matcher = namePattern.matcher(matchString);
                matcherList.add(matcher);

                if (!matcher.matches()) {
                    break;
                } else if (j == matchStrings.length - 1) {
                    HashMap<String, List<Matcher>> res = new HashMap<>(1);
                    res.put(type, matcherList);
                    return res.entrySet().iterator().next();
                }
            }
        }
        return null;
    }

    public static Map.Entry<String, List<Matcher>> matchRegexMatrix(String[][] regexMatrix, String matchStrings) {
        return matchRegexMatrix(regexMatrix, new String[]{matchStrings});
    }

    public static String msToSeconds1DecString(long timeMillis) {
        double seconds = timeMillis / 1000.0; // Convert milliseconds to seconds
        int secondsInt = (int) (seconds * 10); // Multiply by 10 to keep one decimal place
        double formattedSeconds = (double) secondsInt / 10; // Divide by 10 to get back to one decimal place

        return String.valueOf(formattedSeconds);
    }

    /**
     * The milisToMsSMinH function takes a long representing milliseconds and returns a list of four longs,
     * the first being the milliseconds, then seconds, minutes and hours.
     * <p>
     *
     * @param ms Time in milliseconds
     *
     * @return A list of 4 longs {ms, s, min, h}
     *
     */
    public static List<Long> millisToMsSMinH(long ms) {
        ArrayList<Long> res = new ArrayList<>();
        res.add(ms % 1000);
        long s = ms / 1000;
        res.add(s % 60);
        long min = s / 60;
        res.add(min % 60);
        long h = min / 60;
        res.add(h);
        return res;
    }
}

