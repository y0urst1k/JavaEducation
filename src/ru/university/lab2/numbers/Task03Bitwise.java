/**
 * Задание 3: демонстрация побитовых операций.
 * Показаны: &, |, ^, ~, <<, >>, >>>, обмен без временной переменной.
 */
package ru.university.lab2.numbers;

public class Task03Bitwise {
    public void run() {
        demonstrateBitwiseOperators();
        demonstrateShiftDifference();
        testBitUtilities();
        swapVariablesXOR();
    }

    /**
     * Побитовые операции:
     * & — И, | — ИЛИ, ^ — XOR, ~ — инверсия.
     */
    private void demonstrateBitwiseOperators() {
        int a = 5;  // 101
        int b = 3;  // 011
        System.out.printf("5 & 3 = %d (бинарно: 101 & 011 = 001)%n", a & b);
        System.out.printf("5 | 3 = %d (101 | 011 = 111)%n", a | b);
        System.out.printf("5 ^ 3 = %d (101 ^ 011 = 110)%n", a ^ b);
        System.out.printf("~5 = %d (инверсия всех битов)%n", ~a);
    }

    /**
     * >> — арифметический сдвиг (сохраняет знак),
     * >>> — логический (заполняет нулями).
     * На отрицательных числах даёт разный результат.
     */
    private void demonstrateShiftDifference() {
        int n = -8; // Двоичное: ...1111111111111000
        System.out.printf("-8 >> 2 = %d (сохраняет знак)%n", n >> 2);
        System.out.printf("-8 >>> 2 = %d (заполняет нулями — становится положительным)%n", n >>> 2);
    }

    /**
     * Проверка чётности: (n & 1) == 0
     * Проверка степени двойки: (n & (n-1)) == 0 и n > 0
     * Подсчёт единиц: цикл с (n & 1) и сдвигом.
     */
    private void testBitUtilities() {
        int n = 16;
        System.out.printf("%d чётное? %b%n", n, (n & 1) == 0);
        System.out.printf("%d — степень двойки? %b%n", n, n > 0 && (n & (n - 1)) == 0);

        int count = 0;
        int temp = 15; // 1111
        while (temp != 0) {
            count += temp & 1;
            temp >>>= 1;
        }
        System.out.printf("Количество единиц в %d: %d%n", 15, count);
    }

    /**
     * Обмен без временной переменной через XOR.
     * a ^= b; b ^= a; a ^= b;
     */
    private void swapVariablesXOR() {
        int a = 10, b = 20;
        System.out.printf("До обмена: a=%d, b=%d%n", a, b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.printf("После XOR-обмена: a=%d, b=%d%n", a, b);
    }
}
