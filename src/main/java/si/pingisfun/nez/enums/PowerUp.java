package si.pingisfun.nez.enums;


public enum PowerUp {
    INSTA_KILL("Insta Kill"),
    MAX_AMMO("Max Ammo"),
    DOUBLE_GOLD("Double Gold"),
    SHOPPING_SPREE("Shopping Spree"),
    CARPENTER("Carpenter"),
    BONUS_GOLD("Bonus Gold");

    private final String name;

    PowerUp(String name) {
        this.name = name;
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
}