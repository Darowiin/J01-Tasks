package powers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PowerDigitsCalculator calculator = new PowerDigitsCalculator();

        long a = readLongInRange(scanner,
                "Введите a (0 < a ≤ 2^32): ",
                1,
                4294967296L
        );

        int n = readIntInRange(scanner,
                "Введите n (0 ≤ n ≤ 1000): ",
                0,
                1000
        );

        int digits = calculator.countDigits(a, n);
        System.out.println("Количество цифр в a^n: " + digits);

        scanner.close();
    }

    /**
     * Читает целое число long в заданном диапазоне.
     */
    private static long readLongInRange(Scanner scanner, String message, long min, long max) {
        while (true) {
            try {
                System.out.print(message);
                long value = scanner.nextLong();

                if (value < min || value > max) {
                    System.out.println("Ошибка: число должно быть в диапазоне [" + min + "; " + max + "].");
                    continue;
                }
                return value;

            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Читает целое число int в заданном диапазоне.
     */
    private static int readIntInRange(Scanner scanner, String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = scanner.nextInt();

                if (value < min || value > max) {
                    System.out.println("Ошибка: число должно быть в диапазоне [" + min + "; " + max + "].");
                    continue;
                }
                return value;

            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scanner.nextLine();
            }
        }
    }
}
