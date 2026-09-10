package edu.course.lab01;

public class FizzBuzz {
    public static void print() {
        for (int i = 1; i <= 500; i++) {
            boolean div5 = (i % 5 == 0);
            boolean div7 = (i % 7 == 0);

            if (div5 && div7) {
                System.out.println("fizzbuzz");
            } else if (div5) {
                System.out.println("fizz");
            } else if (div7) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
