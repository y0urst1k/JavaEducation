import managers.BookManager;
import managers.MusicManager;

import java.io.IOException;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:h2:mem:studydb";
    private static final String USER = "sa";
    private static final String PASS = "";

    void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Подключение к H2 установлено");

            MusicManager.prepareMusic(conn);

            MusicManager.task1ListMusic(conn);
            MusicManager.task2Filter(conn);
            MusicManager.task3InsertMySong(conn);

            BookManager.task4LoadJson(conn);
            BookManager.task5SortedBooks(conn);
            BookManager.task6BooksBefore2000(conn);
            BookManager.task7AddMe(conn);
            BookManager.task8DropTables(conn);

        } catch (SQLException e) {
            System.err.println("Ошибка базы данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}