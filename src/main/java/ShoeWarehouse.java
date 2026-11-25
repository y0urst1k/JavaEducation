import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShoeWarehouse implements OrderChannel, AutoCloseable {
    public static final List<String> PRODUCT_LIST =
            List.of("Nike", "Adidas", "Puma", "Reebok", "Asics");

    private static final int CAPACITY = 5;

    private final Queue<Order> orders = new ArrayDeque<>();
    private ExecutorService consumers;

    @Override
    public void receiveOrder(Order order) {
        synchronized (orders) {
            while (orders.size() >= CAPACITY) {
                await(orders);
            }
            orders.add(order);
            System.out.printf("[%s] Принят %s (в очереди: %d)%n",
                    Thread.currentThread().getName(), order, orders.size());
            orders.notifyAll();
        }
    }

    @Override
    public Order fulfillOrder() {
        synchronized (orders) {
            while (orders.isEmpty()) {
                await(orders);
            }
            Order order = orders.poll();
            System.out.printf("[%s] Обработан %s (в очереди: %d)%n",
                    Thread.currentThread().getName(), order, orders.size());
            orders.notifyAll();
            return order;
        }
    }

    private static void await(Object monitor) {
        try {
            monitor.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void startFulfilling(int consumerCount, int ordersPerConsumer) {
        consumers = Executors.newFixedThreadPool(consumerCount);
        for (int i = 1; i <= consumerCount; i++) {
            consumers.submit(new OrderConsumer(this, ordersPerConsumer));
        }
    }

    @Override
    public void close() {
        if (consumers == null) return;
        consumers.shutdown();
        try {
            if (!consumers.awaitTermination(30, TimeUnit.SECONDS)) {
                consumers.shutdownNow();
            }
        } catch (InterruptedException e) {
            consumers.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}