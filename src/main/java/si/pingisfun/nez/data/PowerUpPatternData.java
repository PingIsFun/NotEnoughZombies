package si.pingisfun.nez.data;

import java.util.*;

public final class PowerUpPatternData {
    public static final class AlienArcadium {
        public static final List<SortedSet<Integer>> INSTA_KILL = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23)), Collections.emptySortedSet(),
                new TreeSet<>(Arrays.asList(3, 6, 9, 12, 15, 18, 21)), Collections.emptySortedSet()
        );
        public static final List<SortedSet<Integer>> MAX_AMMO = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 5, 8, 12, 16)), new TreeSet<>(Arrays.asList(1, 6)),
                new TreeSet<>(Arrays.asList(3, 6, 9, 13, 17)), new TreeSet<>(Arrays.asList(2, 7))
        );

        public static final List<SortedSet<Integer>> SHOPPING_SPREE = Arrays.asList(
                new TreeSet<>(Arrays.asList(5, 15, 45)), new TreeSet<>(Collections.singleton(5)),
                new TreeSet<>(Collections.singleton(6)), new TreeSet<>(Collections.singleton(6)),
                new TreeSet<>(Collections.singleton(7)), new TreeSet<>(Collections.singleton(7))
        );
    }

    public static final class DeadEnd {
        public static final List<SortedSet<Integer>> INSTA_KILL = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 8, 11, 14, 17, 23)), Collections.emptySortedSet(),
                new TreeSet<>(Arrays.asList(3, 6, 9, 12, 18, 21, 24)), Collections.emptySortedSet()
        );
        public static final List<SortedSet<Integer>> MAX_AMMO = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 8, 12, 16, 21, 26)), Collections.emptySortedSet(),
                new TreeSet<>(Arrays.asList(3, 6, 9, 13, 17, 22, 27)), Collections.emptySortedSet()
        );

    }

    public static final class BadBlood {
        public static final List<SortedSet<Integer>> INSTA_KILL = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 5, 8, 11, 14, 17, 23)), Collections.emptySortedSet(),
                new TreeSet<>(Arrays.asList(3, 6, 9, 12, 18, 21, 24)), Collections.emptySortedSet()
        );

        public static final List<SortedSet<Integer>> MAX_AMMO = Arrays.asList(
                new TreeSet<>(Arrays.asList(2, 5, 8, 12, 16, 21, 26)), Collections.emptySortedSet(),
                new TreeSet<>(Arrays.asList(3, 6, 9, 13, 17, 22, 27)), Collections.emptySortedSet()
        );

    }
}