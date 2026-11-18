import dip.EmailSender;
import dip.NotificationService;
import dip.SmsSender;
import isp.OldPrinter;
import isp.Printable;
import lsp.Bird;
import lsp.Flyable;
import lsp.Penguin;
import lsp.Sparrow;
import ocp.DiscountCalculator;
import ocp.DiscountRegistry;
import srp.ReportManager;

void main() {

    // ---------- S: расчёт, вывод и сохранение разделены ----------
    ReportManager manager = new ReportManager(List.of(5, 10, 15, 20));
    manager.generateReport();

    // ---------- O: стратегии скидок, реестр как точка сборки ----------
    DiscountRegistry registry = new DiscountRegistry();
    DiscountCalculator calculator = new DiscountCalculator(registry);

    IO.println("Regular: " + calculator.calculateDiscount("regular", 1000));
    IO.println("VIP: " + calculator.calculateDiscount("vip", 1000));
    IO.println("Super VIP: " + calculator.calculateDiscount("super_vip", 1000));
    IO.println("Unknown: " + calculator.calculateDiscount("student", 1000));

    // ---------- L: полёт — только для тех, кто умеет ----------
    List<Bird> birds = List.of(new Sparrow(), new Penguin());
    for (Bird bird : birds) {
        bird.eat();
        if (bird instanceof Flyable flyable) {
            flyable.fly();
        } else {
            IO.println(bird.getClass().getSimpleName() + " не летает");
        }
    }

    // ---------- I: клиенты зависят только от нужного интерфейса ----------
    Printable printer = new OldPrinter();
    printer.print("Отчёт за неделю");

    // ---------- D: зависимость внедряется через конструктор ----------
    NotificationService emailService = new NotificationService(new EmailSender());
    emailService.send("Ваш заказ готов к выдаче!");

    NotificationService smsService = new NotificationService(new SmsSender());
    smsService.send("Ваш заказ готов к выдаче!");
}
