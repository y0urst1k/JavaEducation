package edu.course.lab01;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String command = args[0];

        switch (command) {
            case "fizzbuzz":
                if (args.length != 1) {
                    printUsage();
                    return;
                }
                FizzBuzz.print();
                break;

            case "reverse":
                if (args.length != 2) {
                    printUsage();
                    return;
                }
                System.out.println(TextTasks.reverse(args[1]));
                break;

            case "quadratic":
                if (args.length != 4) {
                    printUsage();
                    return;
                }
                QuadraticEquation.solve(
                        Double.parseDouble(args[1]),
                        Double.parseDouble(args[2]),
                        Double.parseDouble(args[3])
                );
                break;

            case "series":
                if (args.length != 1) {
                    printUsage();
                    return;
                }
                SeriesCalculator.calculate();
                break;

            case "palindrome":
                if (args.length != 2) {
                    printUsage();
                    return;
                }
                System.out.println(TextTasks.isPalindrome(args[1]));
                break;

            default:
                printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Использование:");
        System.out.println("  java -cp out edu.course.lab01.Main fizzbuzz");
        System.out.println("  java -cp out edu.course.lab01.Main reverse <строка>");
        System.out.println("  java -cp out edu.course.lab01.Main quadratic <a> <b> <c>");
        System.out.println("  java -cp out edu.course.lab01.Main series");
        System.out.println("  java -cp out edu.course.lab01.Main palindrome <строка>");
    }
}
