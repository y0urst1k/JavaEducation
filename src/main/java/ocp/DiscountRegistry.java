package ocp;

import java.util.HashMap;
import java.util.Map;

public class DiscountRegistry {
    private final Map<String, DiscountStrategy> strategies = new HashMap<>();

    public DiscountRegistry() {
        register("regular", new RegularDiscount());
        register("vip", new VipDiscount());
        register("super_vip", new SuperVipDiscount());
    }

    public void register(String userType, DiscountStrategy strategy) {
        strategies.put(userType, strategy);
    }

    public DiscountStrategy getForUserType(String userType) {
        return strategies.getOrDefault(userType, new NoDiscount());
    }
}
