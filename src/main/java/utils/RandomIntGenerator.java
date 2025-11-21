package utils;

import java.util.Random;

/**
 * Универсальный генератор массивов случайных целых чисел.
 * Поддерживает:
 * - Генерацию массива в заданном диапазоне.
 * - Генерацию с фиксированным seed (для тестов).
 * - Генерацию без seed (обычный режим).
 */
public class RandomIntGenerator {

    private final Random random;

    /**
     * Создаёт генератор с произвольным seed.
     */
    public RandomIntGenerator() {
        this.random = new Random();
    }

    /**
     * Создаёт генератор с фиксированным seed.
     * Используется для воспроизводимых тестов.
     *
     * @param seed значение seed
     */
    public RandomIntGenerator(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Генерирует массив случайных чисел.
     *
     * @param size количество элементов массива
     * @param min минимальное значение (включительно)
     * @param max максимальное значение (включительно)
     * @return массив случайных чисел
     */
    public int[] generate(int size, int min, int max) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    /**
     * Генерирует массив по умолчанию в диапазоне [-100; 100].
     *
     * @param size размер массива
     * @return массив случайных значений
     */
    public int[] generateDefaultRange(int size) {
        return generate(size, -100, 100);
    }
}