/**
 * Задание 7: демонстрация перегрузки методов, varargs и рекурсии.
 * Реализованы:
 * - Перегрузка по количеству и типу аргументов
 * - Varargs для суммирования произвольного числа int
 * - Рекурсивный факториал
 */
package ru.university.lab2.methods;

public class Task07MethodOverloading {
    public void run() {
        demonstrateOverloading();
        demonstrateVarargs();
        demonstrateRecursion();
    }

    /**
     * Перегрузка методов: одинаковое имя, разные параметры.
     */
    private void printValue(int x) {
        System.out.printf("Целое: %d%n", x);
    }

    private void printValue(double x) {
        System.out.printf("Вещественное: %.2f%n", x);
    }

    private void printValue(String x) {
        System.out.printf("Строка: '%s'%n", x);
    }

    private void demonstrateOverloading() {
        System.out.println("=== Перегрузка методов ===");
        printValue(42);
        printValue(3.14);
        printValue("Привет");
    }

    /**
     * Varargs: int... позволяет передать 0 или больше int.
     * Внутри — как массив.
     */
    private int sum(int... numbers) {
        int total = 0;
        for (int x : numbers) {
            total += x;
        }
        return total;
    }

    private void demonstrateVarargs() {
        System.out.println("=== Varargs (суммирование) ===");
        System.out.printf("sum() = %d%n", sum());
        System.out.printf("sum(1, 2) = %d%n", sum(1, 2));
        System.out.printf("sum(1, 2, 3, 4) = %d%n", sum(1, 2, 3, 4));
    }

    /**
     * Рекурсия: факториал с базовым случаем 0 = 1.
     * Для отрицательных — возвращаем -1 как ошибка.
     */
    private long factorial(int n) {
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    private void demonstrateRecursion() {
        System.out.println("=== Рекурсия (факториал) ===");
        System.out.printf("factorial(0) = %d%n", factorial(0));
        System.out.printf("factorial(5) = %d%n", factorial(5));
        System.out.printf("factorial(-1) = %d (ошибка)%n", factorial(-1));
    }
}
