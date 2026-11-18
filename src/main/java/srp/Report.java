package srp;

import java.time.LocalDateTime;

public class Report {
    private final int sum;
    private final double average;
    private final LocalDateTime generatedAt;

    public Report(int sum, double average) {
        this.sum = sum;
        this.average = average;
        this.generatedAt = LocalDateTime.now();
    }

    public int getSum() { return sum; }
    public double getAverage() { return average; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
}
