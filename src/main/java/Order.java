public record Order(int id, String shoeType, int quantity) {
    @Override
    public String toString() {
        return "Заказ №%d [%s × %d]".formatted(id, shoeType, quantity);
    }
}