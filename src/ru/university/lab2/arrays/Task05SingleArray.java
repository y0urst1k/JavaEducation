/**
 * Задание 5: работа с одномерными массивами.
 * Заполнение, поиск мин/макс/среднего, сортировка (выбором), сравнение массивов.
 */
package ru.university.lab2.arrays;

import java.util.Random;

public class Task05SingleArray {
    public void run() {
        int[] arr = createRandomArray(10);
        printArray("Исходный массив", arr);
        printMinMaxAverage(arr);
        sortArray(arr);
        printArray("После сортировки", arr);
        demonstrateArrayComparison();
    }

    private int[] createRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    private void printArray(String label, int[] arr) {
        System.out.print(label + ": ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    private void printMinMaxAverage(int[] arr) {
        int min = arr[0], max = arr[0], sum = 0;
        for (int x : arr) {
            if (x < min) min = x;
            if (x > max) max = x;
            sum += x;
        }
        double avg = (double) sum / arr.length;
        System.out.printf("Мин: %d, Макс: %d, Среднее: %.2f%n", min, max, avg);
    }

    /**
     * Сортировка выбором: на каждом шаге находим минимум и ставим на место.
     */
    private void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    /**
     * arr1 == arr2 — ссылочное равенство (адреса).
     * arr1.equals(arr2) — то же, что == (наследуется от Object).
     */
    private void demonstrateArrayComparison() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = a;

        System.out.printf("a == b? %b (разные объекты)%n", a == b);
        System.out.printf("a.equals(b)? %b (по умолчанию как ==)%n", a.equals(b));
        System.out.printf("a == c? %b (один объект)%n", a == c);
    }
}
