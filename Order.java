public class Order {
    private final Beverage beverage;
    private final String customerName;
    private boolean isMystery;

    public Order(Beverage beverage, String customerName) {
        this.beverage = beverage;
        this.customerName = customerName;
        this.isMystery = false;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isMystery() {
        return isMystery;
    }

    public void setMystery(boolean mystery) {
        isMystery = mystery;
    }
}
