/**
 * Задание 1: демонстрация особенностей целочисленной арифметики.
 * Показаны: переполнение, деление, приведение типов, арифметика char.
 */
package ru.university.lab2.numbers;

public class Task01IntegerTraps {
    public void run() {
        printMinMaxValues();
        demonstrateIntOverflow();
        demonstrateIntOverflowMultiplication();
        demonstrateDivisionAndModulo();
        demonstrateLongToIntCast();
        demonstrateCharArithmetic();
        testAdditionWithOverflowCheck();
    }

    /**
     * Вывод диапазонов для byte, short, int, long.
     */
    private void printMinMaxValues() {
        System.out.println("=== Диапазоны целых типов ===");
        System.out.printf("byte:   от %d до %d%n", Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("short:  от %d до %d%n", Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("int:    от %d до %d%n", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("long:   от %d до %d%n", Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Прибавление 1 к Integer.MAX_VALUE вызывает переполнение.
     * Результат становится отрицательным из-за двоичного представления (переход через границу).
     */
    private void demonstrateIntOverflow() {
        int result = Integer.MAX_VALUE + 1;
        System.out.printf("Integer.MAX_VALUE + 1 = %d (переполнение)%n", result);
    }

    /**
     * Умножение в int переполняется, в long — нет, если привести до умножения.
     */
    private void demonstrateIntOverflowMultiplication() {
        int resultInt = Integer.MAX_VALUE * 2;
        long resultLong = (long) Integer.MAX_VALUE * 2;
        System.out.printf("Integer.MAX_VALUE * 2 (int): %d (переполнение)%n", resultInt);
        System.out.printf("Integer.MAX_VALUE * 2 (long): %d (без переполнения)%n", resultLong);
    }

    /**
     * Остаток от деления в Java сохраняет знак делимого.
     * Например: -5 % 2 = -1, а не 1.
     */
    private void demonstrateDivisionAndModulo() {
        System.out.printf("5 / 2 = %d (целочисленное деление)%n", 5 / 2);
        System.out.printf("-5 / 2 = %d (округление к нулю)%n", -5 / 2);
        System.out.printf("5 %% 2 = %d%n", 5 % 2);
        System.out.printf("-5 %% 2 = %d (остаток сохраняет знак делимого)%n", -5 % 2);
    }

    /**
     * Приведение long > Integer.MAX_VALUE к int приводит к потере старших битов.
     * Результат — "перемотка" в отрицательную часть.
     */
    private void demonstrateLongToIntCast() {
        long big = (long) Integer.MAX_VALUE + 1000;
        int casted = (int) big;
        System.out.printf("Приведение long %d к int: %d (потеря данных)%n", big, casted);
    }

    /**
     * char — это целочисленный тип. Можно складывать и прибавлять.
     * Результат можно интерпретировать как число или символ.
     */
    private void demonstrateCharArithmetic() {
        char c = 'A';
        char next = (char) (c + 1);
        int sum = 'A' + 'B';
        System.out.printf("Следующий символ после '%c': '%c'%n", c, next);
        System.out.printf("'A' + 'B' = %d (как число), как символ: '%c'%n", sum, (char) sum);
    }

    /**
     * Проверка переполнения при сложении двух int.
     * Если b > 0, то a + b > MAX → a > MAX - b.
     * Если b < 0, то a + b < MIN → a < MIN - b.
     */
    private boolean addWithOverflowCheck(int a, int b) {
        if (b > 0) return a > Integer.MAX_VALUE - b;
        if (b < 0) return a < Integer.MIN_VALUE - b;
        return false;
    }

    private void testAdditionWithOverflowCheck() {
        System.out.printf("100 + 200: переполнение? %b%n", addWithOverflowCheck(100, 200));
        System.out.printf("Integer.MAX_VALUE - 10 + 100: переполнение? %b%n", addWithOverflowCheck(Integer.MAX_VALUE - 10, 100));
    }
}
