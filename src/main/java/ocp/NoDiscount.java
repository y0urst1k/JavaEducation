package ocp;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double calculate(double price) {
        return 0.0;
    }
}
