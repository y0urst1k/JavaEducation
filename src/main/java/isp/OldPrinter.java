package isp;

public class OldPrinter implements Printable {
    @Override
    public void print(String document) {
        System.out.println("Печатаю: " + document);
    }
}
