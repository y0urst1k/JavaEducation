public class OrderConsumer implements Runnable {
    private final OrderChannel channel;
    private final int ordersToProcess;

    public OrderConsumer(OrderChannel channel, int ordersToProcess) {
        this.channel = channel;
        this.ordersToProcess = ordersToProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < ordersToProcess; i++) {
            channel.fulfillOrder();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Обработал свои " + ordersToProcess + " заказов");
    }
}