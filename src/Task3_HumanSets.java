import java.util.*;

class Human implements Comparable<Human> {
    String firstName, lastName;
    int age;

    Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.age       = age;
    }

    @Override
    public int compareTo(Human o) {
        int c = lastName.compareTo(o.lastName);
        if (c != 0) return c;
        c = firstName.compareTo(o.firstName);
        if (c != 0) return c;
        return Integer.compare(age, o.age);
    }

    @Override public String toString() { return firstName + " " + lastName + " (" + age + ")"; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human h)) return false;
        return age == h.age
                && Objects.equals(firstName, h.firstName)
                && Objects.equals(lastName,  h.lastName);
    }
    @Override public int hashCode() { return Objects.hash(firstName, lastName, age); }
}

class HumanComparatorByLastName implements Comparator<Human> {
    @Override public int compare(Human a, Human b) { return a.lastName.compareTo(b.lastName); }
}

public class Task3_HumanSets {
    public static void run() {
        List<Human> humans = Arrays.asList(
                new Human("Ivan", "Petrov",   25),
                new Human("Anna", "Sidorova", 30),
                new Human("Petr", "Ivanov",   20),
                new Human("Ivan", "Petrov",   25),  // дубликат
                new Human("Olga", "Kozlova",  28)
        );

        System.out.println("Исходный список: " + humans);

        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("HashSet:         " + hashSet);

        Set<Human> linkedSet = new LinkedHashSet<>(humans);
        System.out.println("LinkedHashSet:   " + linkedSet);

        Set<Human> treeSet = new TreeSet<>(humans);
        System.out.println("TreeSet:         " + treeSet);

        Set<Human> treeByLast = new TreeSet<>(new HumanComparatorByLastName());
        treeByLast.addAll(humans);
        System.out.println("TreeSet(last):   " + treeByLast);

        Set<Human> treeByAge = new TreeSet<>(Comparator.comparingInt(h -> h.age));
        treeByAge.addAll(humans);
        System.out.println("TreeSet(age):    " + treeByAge);

        /*
        Различия в выводах:
        - HashSet: порядок не гарантирован, дубликаты удаляются через equals/hashCode.
        - LinkedHashSet: сохраняет порядок вставки, дубликаты удаляются.
        - TreeSet (без компаратора): сортирует по compareTo (фамилия → имя → возраст), дубликаты удаляются, если compareTo==0.
        - TreeSet с HumanComparatorByLastName: сортирует только по фамилии; если фамилии совпадают, TreeSet считает объекты «равными» и оставляет один.
        - TreeSet по возрасту: сортирует по возрасту; при равных возрастах один из элементов теряется.
        */
    }
}