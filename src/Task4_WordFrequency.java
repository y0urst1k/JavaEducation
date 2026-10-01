import java.util.*;

public class Task4_WordFrequency {
    public static void run() {
        String text = "The quick brown fox jumps over the lazy dog " +
                "The dog barks and the fox runs away";

        String[] words = text.toLowerCase().split("[^a-z]+");

        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            if (w.isEmpty()) continue;
            freq.merge(w, 1, Integer::sum);
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(freq.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Частота слов:");
        for (var e : sorted)
            System.out.printf("  %-12s %d%n", e.getKey(), e.getValue());
    }
}