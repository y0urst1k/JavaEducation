import Task1Reflection.Invoker;
import Task1Reflection.MyClass;
import Task2FileSystemWork.WorkFS;

void main() {
    try {
        MyClass obj = new MyClass();
        System.out.println("=== Вызов аннотированных защищённых и приватных методов ===");
        Invoker.invokeAnnotated(obj);
    } catch (Exception e) {
        System.err.println("Ошибка при выполнении задачи 1: " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }

    try {
        WorkFS.StartWork();
    } catch (IOException e) {
        System.err.println("Ошибка при работе с файловой системой: " + e.getMessage());
    }
}
