/**
 * Задание 2: демонстрация особенностей double и float.
 * Показаны: неточность 0.1+0.2, NaN, Infinity, округление.
 */
package ru.university.lab2.numbers;

public class Task02FloatingPoint {
    public void run() {
        demonstrate01Plus02();
        loopSummationComparison();
        demonstrateInfinityAndNaN();
        demonstrateCastingRounding();
        compareFloatDoublePrecision();
    }

    /**
     * 0.1 и 0.2 не имеют точного двоичного представления.
     * Их сумма не равна 0.3 — это известная проблема IEEE 754.
     */
    private void demonstrate01Plus02() {
        double result = 0.1 + 0.2;
        System.out.printf("0.1 + 0.2 = %.17f (не 0.3 из-за неточности представления)%n", result);
    }

    /**
     * Сложение 0.1 десять раз не даёт ровно 1.0.
     * Сравнение через == — ненадёжно. Нужен эпсилон.
     */
    private void loopSummationComparison() {
        double sum = 0.0;
        for (int i = 0; i < 10; i++) sum += 0.1;
        System.out.printf("Сумма 0.1 * 10 раз = %.17f%n", sum);
        System.out.printf("== 1.0? %b (лучше использовать эпсилон)%n", sum == 1.0);
    }

    /**
     * Деление на 0.0 даёт Infinity, 0.0/0.0 — NaN.
     * NaN никогда не равен самому себе.
     */
    private void demonstrateInfinityAndNaN() {
        double inf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;
        System.out.printf("1.0 / 0.0 = %f (Infinity)%n", inf);
        System.out.printf("-1.0 / 0.0 = %f (-Infinity)%n", negInf);
        System.out.printf("0.0 / 0.0 = %f (NaN)%n", nan);
        System.out.printf("NaN == NaN? %b (всегда false)%n", nan == nan);
    }

    /**
     * (int) — усечение, Math.round() — округление к ближайшему,
     * floor — вниз, ceil — вверх.
     */
    private void demonstrateCastingRounding() {
        double x = 2.7;
        double y = -2.7;
        System.out.printf("(int)%.1f = %d, Math.round(%.1f) = %d%n", x, (int)x, x, Math.round(x));
        System.out.printf("Math.floor(%.1f) = %.1f, Math.ceil(%.1f) = %.1f%n", x, Math.floor(x), x, Math.ceil(x));
        System.out.printf("(int)%.1f = %d, Math.round(%.1f) = %d%n", y, (int)y, y, Math.round(y));
    }

    /**
     * double имеет больше бит мантиссы, чем float — выше точность.
     */
    private void compareFloatDoublePrecision() {
        float f = 1.0f / 3.0f;
        double d = 1.0 / 3.0;
        System.out.printf("float 1/3: %.9f%n", f);
        System.out.printf("double 1/3: %.17f (выше точность)%n", d);
    }
}
