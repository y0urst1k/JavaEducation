public interface OrderChannel {
    void receiveOrder(Order order);
    Order fulfillOrder();
}
