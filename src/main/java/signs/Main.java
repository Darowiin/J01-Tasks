package signs;

import utils.RandomIntGenerator;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();

        System.out.print("Введите количество чисел: ");
        int n;
        try {
            n = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("0");
            return;
        }
        if (n <= 0) {
            System.out.println("0");
            return;
        }

        int[] array = randomIntGenerator.generateDefaultRange(n);

        System.out.println("Сгенерированный массив (диапазон [-100; 100]):");
        System.out.println(Arrays.toString(array));

        int maxLen = SignSeriesCounter.maxSignRunLength(array);

        System.out.println(maxLen);
    }
}
