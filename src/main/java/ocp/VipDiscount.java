package ocp;

public class VipDiscount implements DiscountStrategy {
    @Override
    public double calculate(double price) {
        return price * 0.1;
    }
}
