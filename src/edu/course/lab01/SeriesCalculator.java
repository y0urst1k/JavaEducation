package edu.course.lab01;

public class SeriesCalculator {
    public static void calculate() {
        double sum = 0;
        int n = 2;
        int count = 0;
        double epsilon = 1e-6;

        while (true) {
            double term = 1.0 / (n * n + n - 2);

            if (Math.abs(term) < epsilon) {
                break;
            }

            sum += term;
            count++;
            n++;
        }

        int lastN = n - 1;

        System.out.println("Сумма ряда: " + sum);
        System.out.println("Последний добавленный n: " + lastN);
        System.out.println("Количество добавленных членов: " + count);
    }
}
