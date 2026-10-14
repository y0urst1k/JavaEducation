package app;

import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.FigureStorage;
import storage.TextFigureStorage;
import storage.SerialFigureStorage;

import exceptions.InvalidLineException;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Путь к входному текстовому файлу: ");
        String inputPathStr = scanner.nextLine().trim();
        Path inputPath = Paths.get(inputPathStr);

        System.out.print("Путь к выходному бинарному файлу: ");
        String outputPathStr = scanner.nextLine().trim();
        Path outputPath = Paths.get(outputPathStr);

        FigureStorage textStorage = new TextFigureStorage();
        FigureStorage serialStorage = new SerialFigureStorage();

        List<Figure> figures;
        try {
            figures = textStorage.read(inputPath);
        } catch (InvalidLineException e) {
            System.err.println("Ошибка при чтении текстового файла: строка " + e.getLineNumber() + ": " + e.getMessage());
            // Если критично — можно завершить, здесь продолжаем с пустым списком
            figures = Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            figures = Collections.emptyList();
        }

        // Вывод характеристик фигур
        System.out.println("\nСписок фигур:");
        for (Figure f : figures) {
            System.out.printf("%s | площадь=%.3f | периметр=%.3f%n",
                    f.toString(), f.area(), f.perimeter());
        }

        // Создание цилиндра
        if (!figures.isEmpty()) {
            Random random = new Random();
            Figure base = figures.get(random.nextInt(figures.size()));

            System.out.print("\nВведите высоту цилиндра: ");
            double height;
            try {
                height = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Некорректное число, используем высоту 1.0");
                height = 1.0;
            }

            Cylinder cylinder = new Cylinder(base, height);
            System.out.println("\nЦилиндр:");
            System.out.println("Основание: " + cylinder.getBase());
            System.out.println("Высота: " + height);
            System.out.printf("Объём: %.3f%n", cylinder.volume());
        } else {
            System.out.println("\nСписок фигур пуст — пропускаем создание цилиндра.");
        }

        // Сохранение в бинарный файл
        try {
            serialStorage.write(outputPath, figures);
            System.out.println("\nСписок успешно сохранён в бинарный файл: " + outputPath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в бинарный файл: " + e.getMessage());
        }

        scanner.close();
    }
}
