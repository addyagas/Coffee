public class Barista {
    public void prepareOrder(Order order) {
        System.out.println("preparing " + order.getBeverage().getDrinkType() + " for " + order.getCustomerName());
    }

    public void serveOrder(Order order) {
        if (order.isMystery()) {
            System.out.println("\n****** MYSTERY ORDER **** coming up for " + order.getCustomerName() + "! guess the drink for a discount or swap.\n");
        } else {
            System.out.println(order.getCustomerName() + ", your " + order.getBeverage().getDrinkType() + " is ready!!!!!");
        }
    }
}
