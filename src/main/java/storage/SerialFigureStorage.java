package storage;

import geometry2d.Figure;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SerialFigureStorage implements FigureStorage {
    @Override
    @SuppressWarnings("unchecked")
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(path.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (!(obj instanceof List<?>)) {
                throw new IOException("Файл не содержит список фигур");
            }
            List<?> raw = (List<?>) obj;
            List<Figure> result = new ArrayList<>();
            for (Object o : raw) {
                if (!(o instanceof Figure)) {
                    throw new IOException("Элемент списка не является Figure: " + o.getClass());
                }
                result.add((Figure) o);
            }
            return result;
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(figures);
        }
    }
}
