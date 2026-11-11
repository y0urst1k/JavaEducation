package managers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Book;
import models.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

public class BookManager {
    public static void task4LoadJson(Connection conn) throws SQLException, IOException {
        System.out.println("\n=== №4. Загрузка данных из books.json ===");

        String createVisitors = "CREATE TABLE IF NOT EXISTS visitors (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "surname VARCHAR(100) NOT NULL, " +
                "phone VARCHAR(30) NOT NULL UNIQUE, " +
                "subscribed BOOLEAN NOT NULL)";
        String createBooks = "CREATE TABLE IF NOT EXISTS books (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "visitor_id INT NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "author VARCHAR(255), " +
                "publishing_year INT, " +
                "isbn VARCHAR(20), " +
                "publisher VARCHAR(255))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createVisitors);
            stmt.execute(createBooks);
        }

        List<Visitor> visitors;
        try (InputStream is = BookManager.class.getResourceAsStream("/books.json")) {
            if (is == null) {
                throw new IllegalStateException("books.json не найден в resources");
            }
            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                visitors = new Gson().fromJson(reader, new TypeToken<List<Visitor>>() {}.getType());
            }
        }

        for (Visitor v : visitors) {
            int visitorId = insertVisitorIfNotExists(conn, v);
            if (v.getFavoriteBooks() == null) continue;
            for (Book b : v.getFavoriteBooks()) {
                insertBookIfNotExists(conn, visitorId, b);
            }
        }

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM visitors")) {
            rs.next();
            System.out.println("Посетителей в базе: " + rs.getInt(1));
        }
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books")) {
            rs.next();
            System.out.println("Книг в базе: " + rs.getInt(1));
        }
    }

    public static int insertVisitorIfNotExists(Connection conn, Visitor v) throws SQLException {
        String find = "SELECT id FROM visitors WHERE phone = ?";
        try (PreparedStatement ps = conn.prepareStatement(find)) {
            ps.setString(1, v.getPhone());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }

        String insert = "INSERT INTO visitors (name, surname, phone, subscribed) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getName());
            ps.setString(2, v.getSurname());
            ps.setString(3, v.getPhone());
            ps.setBoolean(4, v.isSubscribed());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getInt(1);
            }
        }
    }

    public static void insertBookIfNotExists(Connection conn, int visitorId, Book b) throws SQLException {
        boolean hasIsbn = b.getIsbn() != null && !b.getIsbn().isBlank();

        String find = hasIsbn
                ? "SELECT id FROM books WHERE isbn = ?"
                : "SELECT id FROM books WHERE name = ? AND author = ?";

        try (PreparedStatement ps = conn.prepareStatement(find)) {
            if (hasIsbn) {
                ps.setString(1, b.getIsbn());
            } else {
                ps.setString(1, b.getName());
                ps.setString(2, b.getAuthor());
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return;
            }
        }

        String insert = "INSERT INTO books (visitor_id, name, author, publishing_year, isbn, publisher) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setInt(1, visitorId);
            ps.setString(2, b.getName());
            ps.setString(3, b.getAuthor());
            ps.setInt(4, b.getPublishingYear());
            ps.setString(5, b.getIsbn());
            ps.setString(6, b.getPublisher());
            ps.executeUpdate();
        }
    }

    public static void task5SortedBooks(Connection conn) throws SQLException {
        System.out.println("\n=== №5. Книги по году издания ===");
        String sql = "SELECT DISTINCT name, author, publishing_year FROM books ORDER BY publishing_year";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("  %d — %s (%s)%n", rs.getInt("publishing_year"), rs.getString("name"), rs.getString("author"));
            }
        }
    }

    public static void task6BooksBefore2000(Connection conn) throws SQLException {
        System.out.println("\n=== №6. Книги до 2000 года ===");
        String sql = "SELECT name, author, publishing_year FROM books WHERE publishing_year < 2000 ORDER BY publishing_year";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("  %d — %s (%s)%n", rs.getInt("publishing_year"), rs.getString("name"), rs.getString("author"));
            }
        }
    }

    public static void task7AddMe(Connection conn) throws SQLException {
        System.out.println("\n=== №7. Добавление себя и своих книг ===");
        String myPhone = "+7-900-000-00-00";

        Visitor me = new Visitor("Дмитрий", "Юркин", myPhone, List.of(), true);
        int myId = insertVisitorIfNotExists(conn, me);

        String insertBook = "INSERT INTO books (visitor_id, name, author, publishing_year, isbn, publisher) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        String[][] myBooks = {
                {"Nevernight", "J. Christoff.", "2016", "9781466885035", "Thomas Dunne Books"},
                {"Mentor of the black spider", "Nina Malkina", "2023", "9785041737443", "Eksmo"}
        };
        try (PreparedStatement ps = conn.prepareStatement(insertBook)) {
            for (String[] row : myBooks) {
                ps.setInt(1, myId);
                ps.setString(2, row[0]);
                ps.setString(3, row[1]);
                ps.setInt(4, Integer.parseInt(row[2]));
                ps.setString(5, row[3]);
                ps.setString(6, row[4]);
                ps.executeUpdate();
            }
        }

        String readVisitor = "SELECT id, name, surname, phone, subscribed FROM visitors WHERE phone = ?";
        try (PreparedStatement ps = conn.prepareStatement(readVisitor)) {
            ps.setString(1, myPhone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("Найден: %s %s (тел %s, подписка %b)%n",
                            rs.getString("name"), rs.getString("surname"),
                            rs.getString("phone"), rs.getBoolean("subscribed"));
                    int id = rs.getInt("id");
                    String readBooks = "SELECT name, author, publishing_year FROM books WHERE visitor_id = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(readBooks)) {
                        ps2.setInt(1, id);
                        try (ResultSet rs2 = ps2.executeQuery()) {
                            while (rs2.next()) {
                                System.out.printf("  книга: %s (%s), %d%n",
                                        rs2.getString("name"), rs2.getString("author"),
                                        rs2.getInt("publishing_year"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void task8DropTables(Connection conn) throws SQLException {
        System.out.println("\n=== №8. Удаление таблиц ===");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS books");
            stmt.execute("DROP TABLE IF EXISTS visitors");
        }
        System.out.println("Таблицы books и visitors удалены");
    }
}
