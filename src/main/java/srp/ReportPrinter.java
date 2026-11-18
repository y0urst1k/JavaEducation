package srp;

public class ReportPrinter {
    public void print(Report report) {
        System.out.println("=== Report ===");
        System.out.println("Sum: " + report.getSum());
        System.out.println("Average: " + report.getAverage());
        System.out.println("Generated at: " + report.getGeneratedAt());
    }
}
