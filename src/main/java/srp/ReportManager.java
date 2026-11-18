package srp;

import java.util.List;

public class ReportManager {
    private final ReportCalculator calculator;
    private final ReportPrinter printer;
    private final ReportSaver saver;

    public ReportManager(List<Integer> data) {
        this.calculator = new ReportCalculator(data);
        this.printer = new ReportPrinter();
        this.saver = new ReportSaver("report.txt");
    }

    public void generateReport() {
        Report report = calculator.calculate();
        printer.print(report);
        saver.save(report);
    }
}
