package ocp;

public class DiscountCalculator {
    private final DiscountRegistry registry;

    public DiscountCalculator(DiscountRegistry registry) {
        this.registry = registry;
    }

    public double calculateDiscount(String userType, double price) {
        DiscountStrategy strategy = registry.getForUserType(userType);
        return strategy.calculate(price);
    }
}
