/**
 * Задание 8: консольное меню с использованием:
 * - switch как выражение
 * - текстовых блоков (text blocks) для многострового вывода
 * - вызовом run() других задач
 */
package ru.university.lab2.menu;

import ru.university.lab2.numbers.Task01IntegerTraps;
import ru.university.lab2.numbers.Task02FloatingPoint;
import ru.university.lab2.numbers.Task03Bitwise;
import ru.university.lab2.strings.Task04TextProcessing;
import ru.university.lab2.arrays.Task05SingleArray;
import ru.university.lab2.arrays.Task06Matrix;
import ru.university.lab2.methods.Task07MethodOverloading;

import java.util.Scanner;

public class Task08ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            showMenu();
            int choice = readChoice();

            if (choice == 0) {
                System.out.println("До свидания!");
                break;
            }

            executeTask(choice);
        }
    }

    private void showMenu() {
        String menu = """
                \n=== Меню лабораторной работы ===
                1 — Целочисленные ловушки
                2 — Вещественная арифметика
                3 — Побитовые операции
                4 — Обработка текста
                5 — Одномерные массивы
                6 — Матрицы
                7 — Перегрузка и рекурсия
                0 — Выход
                Выберите задание:\s""";
        System.out.print(menu);
    }

    /**
     * Чтение числа с клавиатуры.
     * Простая проверка — если не int, завершаем.
     */
    private int readChoice() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next(); // сброс некорректного ввода
            return -1;
        }
    }

    /**
     * Выполнение задачи через switch как выражение.
     */
    private void executeTask(int choice) {
        String result = switch (choice) {
            case 1 -> { new Task01IntegerTraps().run(); yield "Выполнено: Задание 1"; }
            case 2 -> { new Task02FloatingPoint().run(); yield "Выполнено: Задание 2"; }
            case 3 -> { new Task03Bitwise().run(); yield "Выполнено: Задание 3"; }
            case 4 -> { new Task04TextProcessing().run(); yield "Выполнено: Задание 4"; }
            case 5 -> { new Task05SingleArray().run(); yield "Выполнено: Задание 5"; }
            case 6 -> { new Task06Matrix().run(); yield "Выполнено: Задание 6"; }
            case 7 -> { new Task07MethodOverloading().run(); yield "Выполнено: Задание 7"; }
            default -> "Неверный номер задания.";
        };
        System.out.println(result);
    }
}
