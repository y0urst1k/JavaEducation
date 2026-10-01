import java.util.Iterator;

public class Task2_PrimesGeneratorTest {
    public static void run() {
        int N = 10;
        Task2_PrimesGenerator gen = new Task2_PrimesGenerator(N);

        System.out.println("Прямой порядок:");
        for (Iterator<Integer> it = gen.iterator(); it.hasNext(); )
            System.out.print(it.next() + " ");
        System.out.println();

        System.out.println("Обратный порядок:");
        for (Iterator<Integer> it = gen.reverseIterator(); it.hasNext(); )
            System.out.print(it.next() + " ");
        System.out.println();
    }
}
