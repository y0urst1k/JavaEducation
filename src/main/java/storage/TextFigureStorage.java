package storage;

import geometry2d.*;
import exceptions.InvalidLineException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class TextFigureStorage implements FigureStorage {
    @Override
    public List<Figure> read(Path path) throws InvalidLineException {
        List<Figure> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue; // пропускаем пустые строки и комментарии
                }
                try {
                    Figure f = parseLine(trimmed);
                    result.add(f);
                } catch (RuntimeException e) {
                    // RuntimeException от конструктора (GeometryException) — оборачиваем в проверяемое
                    throw new InvalidLineException(lineNumber, "Ошибка при создании фигуры: " + e.getMessage(), e);
                } catch (Exception e) {
                    // на случай любых других ошибок парсинга
                    System.err.println("Строка " + lineNumber + ": пропущена из-за ошибки: " + e.getMessage());
                    // продолжаем, не прерываем весь файл
                }
            }
        } catch (IOException e) {
            throw new InvalidLineException(-1, "Ошибка чтения файла", e);
        }
        return result;
    }

    private Figure parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 0) throw new IllegalArgumentException("Пустая строка");

        String type = parts[0].toUpperCase();

        switch (type) {
            case "CIRCLE":
                if (parts.length != 2) throw new IllegalArgumentException("Формат CIRCLE: CIRCLE <radius>");
                double radius = Double.parseDouble(parts[1]);
                return new Circle(radius);

            case "RECTANGLE":
                if (parts.length != 3) throw new IllegalArgumentException("Формат RECTANGLE: RECTANGLE <width> <height>");
                double w = Double.parseDouble(parts[1]);
                double h = Double.parseDouble(parts[2]);
                return new Rectangle(w, h);

            default:
                throw new IllegalArgumentException("Неизвестный тип фигуры: " + type);
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Figure f : figures) {
                if (f instanceof Circle) {
                    Circle c = (Circle) f;
                    writer.write("CIRCLE " + c.getRadius());
                } else if (f instanceof Rectangle) {
                    Rectangle r = (Rectangle) f;
                    writer.write("RECTANGLE " + r.getWidth() + " " + r.getHeight());
                } else {
                    throw new IllegalArgumentException("В списке только Circle и Rectangle: " + f);
                }
                writer.newLine();
            }
        }
    }
}
