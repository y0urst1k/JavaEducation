package srp;

import java.io.FileWriter;
import java.io.IOException;

public class ReportSaver {
    private final String path;

    public ReportSaver(String path) {
        this.path = path;
    }

    public void save(Report report) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("=== Report ===\n");
            writer.write("Sum: " + report.getSum() + "\n");
            writer.write("Average: " + report.getAverage() + "\n");
            writer.write("Generated at: " + report.getGeneratedAt() + "\n");
            System.out.println("Report saved to " + path);
        } catch (IOException e) {
            System.out.println("Error writing report file: " + e.getMessage());
        }
    }
}
