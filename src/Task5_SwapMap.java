import java.util.*;

public class Task5_SwapMap {

    public static <K, V> Map<V, K> swap(Map<K, V> original) {
        Map<V, K> swapped = new HashMap<>();
        for (var entry : original.entrySet()) {
            swapped.put(entry.getValue(), entry.getKey());
        }
        return swapped;
    }

    public static void run() {
        Map<String, Integer> original = Map.of(
                "one",   1,
                "two",   2,
                "three", 3,
                "four",  4
        );
        System.out.println("Исходная:  " + original);
        System.out.println("Обмен:     " + swap(original));

        Map<String, Integer> dup = Map.of("a", 10, "b", 10);
        System.out.println("Дубликаты значений: " + dup + " -> " + swap(dup));
    }
}