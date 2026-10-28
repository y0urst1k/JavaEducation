import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LibraryMain {

    public static void main(String[] args) throws IOException {
        String json = Files.readString(Paths.get("books.json"));
        Gson gson = new Gson();

        // Десериализация в List<Visitor>
        List<Visitor> visitors = gson.fromJson(json, new TypeToken<List<Visitor>>() {}.getType());

        // №1. Посетители библиотеки
        System.out.println("=== №1. Список посетителей и их количество ===");
        visitors.forEach(v -> System.out.printf("%s %s (тел: %s, подписка: %b)%n",
                v.getName(), v.getSurname(), v.getPhone(), v.isSubscribed()));
        System.out.println("Всего посетителей: " + visitors.size());

        // №2. Книги без повторений
        System.out.println("\n=== №2. Все книги без повторений и их количество ===");
        List<Book> allBooksUnique = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .distinct()
                .toList();

        allBooksUnique.forEach(b -> System.out.printf("%s (%s), %d, ISBN: %s%n",
                b.getName(), b.getAuthor(), b.getPublishingYear(), b.getIsbn()));
        System.out.println("Уникальных книг: " + allBooksUnique.size());

        // №3. Сортировка книг по году издания
        System.out.println("\n=== №3. Книги, отсортированные по году издания ===");
        List<Book> sortedByYear = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .distinct()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .toList();
        sortedByYear.forEach(b -> System.out.printf("%d — %s (%s)%n", b.getPublishingYear(), b.getName(), b.getAuthor()));

        // №4. Поиск автора Jane Austen
        System.out.println("\n=== №4. Есть ли у кого-то книга Jane Austen? ===");
        boolean hasJaneAusten = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .anyMatch(b -> "Jane Austen".equals(b.getAuthor()));
        System.out.println(hasJaneAusten ? "Да, есть произведение автора Jane Austen." : "Нет произведений автора Jane Austen.");

        // №5. Максимальное число книг у одного посетителя
        System.out.println("\n=== №5. Максимальное число книг, добавленных одним посетителем ===");
        long maxBooks = visitors.stream()
                .mapToLong(v -> v.getFavoriteBooks() != null ? v.getFavoriteBooks().size() : 0)
                .max()
                .orElse(0);
        System.out.println("Максимальное количество книг у одного посетителя: " + maxBooks);

        // №6. Сообщения подписчикам
        System.out.println("\n=== №6. SMS-сообщения для подписчиков ===");
        double averageBooks = visitors.stream()
                .mapToInt(v -> v.getFavoriteBooks() != null ? v.getFavoriteBooks().size() : 0)
                .average()
                .orElse(0.0);

        List<SmsMessage> smsMessages = visitors.stream()
                .filter(Visitor::isSubscribed)
                .map(v -> {
                    int count = v.getFavoriteBooks() != null ? v.getFavoriteBooks().size() : 0;
                    String message;
                    if (count > averageBooks) {
                        message = "you are a bookworm";
                    } else if (count < averageBooks) {
                        message = "read more";
                    } else {
                        message = "fine";
                    }
                    return new SmsMessage(v.getPhone(), message);
                })
                .toList();

        smsMessages.forEach(m -> System.out.printf("Тел: %s — %s%n", m.getPhoneNumber(), m.getMessage()));
        System.out.println("Сгенерировано SMS: " + smsMessages.size());
    }
}