public abstract class Beverage {
    private final String drinkType;

    public Beverage(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkType() {
        return drinkType;
    }
}
