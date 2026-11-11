package managers;

import org.h2.tools.RunScript;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class MusicManager {
    public static void prepareMusic(Connection conn) throws SQLException, IOException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS study");
        }

        try (InputStream is = MusicManager.class.getResourceAsStream("/music-create.sql")) {
            if (is == null) {
                throw new IllegalStateException("/music-create.sql не найден в resources");
            }
            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                RunScript.execute(conn, reader);
            }
        }
        System.out.println("\nСхема study и таблица music созданы\n");
    }

    public static void task1ListMusic(Connection conn) throws SQLException {
        System.out.println("=== №1. Список композиций ===");
        String sql = "SELECT id, name FROM study.music ORDER BY id";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("  %d — %s%n", rs.getInt("id"), rs.getString("name"));
            }
        }
    }

    public static void task2Filter(Connection conn) throws SQLException {
        System.out.println("\n=== №2. Композиции без 'm' и без 't' ===");
        String sql = "SELECT id, name FROM study.music " +
                "WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%' " +
                "ORDER BY id";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("  %d — %s%n", rs.getInt("id"), rs.getString("name"));
            }
        }
    }

    public static void task3InsertMySong(Connection conn) throws SQLException {
        System.out.println("\n=== №3. Добавление своей композиции ===");
        String sql = "INSERT INTO study.music (id, name) " +
                "SELECT COALESCE(MAX(id), 0) + 1, ? FROM study.music";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "September Rain");
            int rows = ps.executeUpdate();
            System.out.println("Добавлено строк: " + rows);
        }
    }
}
