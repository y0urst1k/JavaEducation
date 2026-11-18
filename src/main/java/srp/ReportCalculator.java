package srp;

import java.util.List;

public class ReportCalculator {
    private final List<Integer> data;

    public ReportCalculator(List<Integer> data) {
        this.data = data;
    }

    public Report calculate() {
        int sum = 0;
        for (int n : data) {
            sum += n;
        }
        double avg = data.isEmpty() ? 0 : (double) sum / data.size();
        return new Report(sum, avg);
    }
}
