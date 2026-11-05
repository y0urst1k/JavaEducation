package Task2FileSystemWork;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class WorkFS  {
    private static final String SURNAME = "Yurkin";
    private static final String NAME = "Dmitry";

    public static void StartWork() throws IOException {
        Path surnameDir = Paths.get(SURNAME);

        Files.createDirectories(surnameDir);

        Path nameFile = surnameDir.resolve(NAME);
        if (Files.notExists(nameFile)) {
            Files.createFile(nameFile);
        }

        Path deepDir = surnameDir.resolve("dir1").resolve("dir2").resolve("dir3");
        Files.createDirectories(deepDir);
        Files.copy(nameFile, deepDir.resolve(NAME), StandardCopyOption.REPLACE_EXISTING);

        // 4-5. file1 в dir1 и file2 в dir2
        Files.createFile(surnameDir.resolve("dir1").resolve("file1"));
        Files.createFile(surnameDir.resolve("dir1").resolve("dir2").resolve("file2"));

        // 6. Рекурсивный обход с пометками D: и F:
        System.out.println("=== Обход директории " + SURNAME + " ===");
        try (Stream<Path> paths = Files.walk(surnameDir)) {
            paths.forEach(p -> {
                String prefix = Files.isDirectory(p) ? "D: " : "F: ";
                System.out.println(prefix + p);
            });
        }

        // 7. Удаление dir1 со всем содержимым
        Path dir1 = surnameDir.resolve("dir1");
        try (Stream<Path> paths = Files.walk(dir1)) {
            paths.sorted(Comparator.reverseOrder())
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            System.err.println("Не удалось удалить " + p + ": " + e.getMessage());
                        }
                    });
        }

        System.out.println("\n=== После удаления dir1 ===");
        try (Stream<Path> paths = Files.walk(surnameDir)) {
            paths.forEach(p -> {
                String prefix = Files.isDirectory(p) ? "D: " : "F: ";
                System.out.println(prefix + p);
            });
        }
    }
}
