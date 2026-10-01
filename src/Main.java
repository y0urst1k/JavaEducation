import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Выберите задание ===");
            System.out.println("1. Методы Collections");
            System.out.println("2. Генератор простых чисел (Iterator)");
            System.out.println("3. Множества и сравнение объектов (Human)");
            System.out.println("4. Частота слов (HashMap)");
            System.out.println("5. Обмен ключей и значений (Map swap)");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Введите число.");
                scanner.next(); // очистить неверный ввод
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> Task1_Collections.run();
                case 2 -> Task2_PrimesGeneratorTest.run();
                case 3 -> Task3_HumanSets.run();
                case 4 -> Task4_WordFrequency.run();
                case 5 -> Task5_SwapMap.run();
                case 0 -> {
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Нет такого задания.");
            }
        }
    }
}