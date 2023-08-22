package si.pingisfun.nez.enums;

public enum ZombiesMap {
    DEAD_END("Dead End"),
    BAD_BLOOD("Bad Blood"),
    ALIEN_ARCADIUM("Alien Arcadium");

    private final String name;

    ZombiesMap(String name) {
        this.name = name;
    }

    public static ZombiesMap getMapByName(String name) {
        for (ZombiesMap map : ZombiesMap.values()) {
            if (map.name.equalsIgnoreCase(name)) {
                return map;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
