public class SimpleQueue {
    private final Order[] orders;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public SimpleQueue(int capacity) {
        this.capacity = capacity;
        this.orders = new Order[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(Order order) {
        if (isFull()) {
            System.out.println("queue is currently full. we cannot add more orders.");
            return;
        }
        rear = (rear + 1) % capacity;
        orders[rear] = order;
        size++;
    }

    public Order dequeue() {
        if (isEmpty()) {
            System.out.println("the queue is now empty. no more orders to process.");
            return null;
        }
        Order order = orders[front];
        front = (front + 1) % capacity;
        size--;
        return order;
    }
}
