import java.util.Random;

public class CoffeeShop {
    private final SimpleQueue orderQueue; //order queue
    private final Barista barista;

    public CoffeeShop(int capacity) {
        this.orderQueue = new SimpleQueue(capacity); //queue capacity set by the user
        this.barista = new Barista();
    }

    public void placeOrder(Beverage beverage, Customer customer) {
        Order order = new Order(beverage, customer.getName());
        if (new Random().nextInt(10) == 0) { //10% chance for a mystery order
            order.setMystery(true);
        }
        orderQueue.enqueue(order); //enqueue adds the order object to the queue
        System.out.println("New order placed: " + beverage.getDrinkType() + " for " + customer.getName());
    }

    public void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.dequeue();
            if (order != null) {
                barista.prepareOrder(order);
                barista.serveOrder(order);
            }
        }
    }

    public SimpleQueue getOrderQueue() {
        return orderQueue;
    }
}
