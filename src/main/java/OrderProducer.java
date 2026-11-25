public class OrderProducer implements Runnable {
    private final OrderChannel channel;
    private final int totalOrders;

    public OrderProducer(OrderChannel channel, int totalOrders) {
        this.channel = channel;
        this.totalOrders = totalOrders;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalOrders; i++) {
            String type = ShoeWarehouse.PRODUCT_LIST.get(i % ShoeWarehouse.PRODUCT_LIST.size());
            channel.receiveOrder(new Order(i, type, 1 + i % 5));
        }
        System.out.println("[Producer] Все заказы отправлены");
    }
}