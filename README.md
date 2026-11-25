# Лабораторная работа 8
- ФИО: Юркин Дмитрий Алексеевич
- Группа: ФИТ-251

# Выбранная СУБД
**H2 Database** в режиме embedded с хранением в памяти

# Подготовка базы данных

Установка и ручная подготовка СУБД не требуются — база создаётся
и наполняется программой автоматически при каждом запуске:

1. При старте программа открывает соединение
   `jdbc:h2:mem:studydb` (пользователь `sa`, пароль пустой).
2. Выполняется скрипт `src/main/resources/music-create.sql`
   (через `org.h2.tools.RunScript`): создаётся схема `study`
   и таблица `music`, загружается список композиций.
3. Выполняются DDL-запросы, создающие таблицы `visitors` и `books`.
4. Данные посетителей и книг загружаются из файла
   `src/main/resources/books.json` и вставляются через
   `PreparedStatement`.

Файлы `music-create.sql` и `books.json` находятся в каталоге
`src/main/resources` и попадают в classpath при сборке Maven.

# Сборка и запуск

Требуется JDK 25 и Maven (либо bundled Maven Wrapper).

Сборка (создаёт `target/lab8.jar`):

```powershell
.\mvnw.cmd clean package