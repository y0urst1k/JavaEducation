package Task1Reflection;

public class MyClass {
    public void publicMethodOne() {
        System.out.println("publicMethodOne вызван");
    }

    public void publicMethodTwo(int x) {
        System.out.println("publicMethodTwo вызван с x = " + x);
    }

    @Repeat(times = 2)
    protected void protectedMethodOne() {
        System.out.println("protectedMethodOne вызван");
    }

    @Repeat(times = 3)
    protected String protectedMethodTwo(String word) {
        String result = "Защищённый метод получил: " + word;
        System.out.println(result);
        return result;
    }


    @Repeat(times = 4)
    private void privateMethodOne() {
        System.out.println("privateMethodOne вызван");
    }

    @Repeat(times = 1)
    private int privateMethodTwo(int a, int b) {
        int sum = a + b;
        System.out.println("privateMethodTwo: " + a + " + " + b + " = " + sum);
        return sum;
    }
}
