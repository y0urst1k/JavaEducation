# Лабораторная работа 5
- ФИО: Юркин Дмитрий Алексеевич
- Группа: ФИТ-251

# Сборка и запуск
- ./mvnw clean package (или ./mvnw.cmd clean package в Windows).
- java -jar target/lab5.jar.

# Проверка тестов

Для проверки работы тестов была намеренно внесена ошибка в метод Rectangle.area(): return width * height * 2;

При запуске mvn test тесты areaOfThreeByFourRectangleIsTwelve, areaAndPerimeterOfSquare и другие упали с сообщением expected: X but was: Y.
Ошибка была исправлена, все тесты проходят, сборка mvn package завершается успешно.