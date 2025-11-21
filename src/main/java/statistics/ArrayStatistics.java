package statistics;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Класс для статистической обработки массивов целых чисел.
 * Предоставляет методы для вычисления:
 * <ul>
 * <li>Моды (наиболее часто встречающиеся значения)</li>
 * <li>Медианы</li>
 * <li>Среднего арифметического</li>
 * <li>Дисперсии</li>
 * <li>Геометрического среднего</li>
 * <li>Перемешивания элементов массива</li>
 * <li>Выборки случайных элементов массива</li>
 * </ul>
 */
public class ArrayStatistics {

    /** Исходный массив целых чисел для анализа */
    private final int[] array;

    /**
     * Создает объект статистики для заданного массива.
     *
     * @param array массив целых чисел; может быть пустым, но не null
     */
    public ArrayStatistics(int[] array) {
        this.array = array;
    }

    /**
     * Вычисляет моду массива — значение, встречающееся чаще всего.
     * Если несколько значений встречаются одинаково часто, возвращает все.
     *
     * @return массив наиболее часто встречающихся значений, пустой массив, если исходный массив пуст
     */
    public int[] getMode() {
        if (array.length == 0) {
            return new int[0];
        }

        Map<Integer, Long> countMap = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        long maxCount = countMap.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);

        return countMap.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    /**
     * Вычисляет медиану массива.
     * Для нечетного числа элементов возвращает средний элемент.
     * Для четного числа элементов возвращает среднее арифметическое двух центральных элементов.
     *
     * @return медиана, или Double.NaN, если массив пуст
     */
    public double getMedian() {
        if (array.length == 0) {
            return Double.NaN;
        }

        int[] sorted = array.clone();
        Arrays.sort(sorted);

        int n = sorted.length;
        if (n % 2 != 0) {
            return sorted[n / 2];
        } else {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        }
    }

    /**
     * Вычисляет среднее арифметическое массива.
     *
     * @return среднее значение, или Double.NaN, если массив пуст
     */
    public double getAverage() {
        if (array.length == 0) {
            return Double.NaN;
        }

        return Arrays.stream(array).average().orElse(0.0);
    }

    /**
     * Вычисляет дисперсию массива (среднее квадратичное отклонение от среднего).
     *
     * @return дисперсия, или Double.NaN, если массив пуст
     */
    public double getVariance() {
        if (array.length == 0) {
            return Double.NaN;
        }

        double mean = getAverage();

        return Arrays.stream(array)
                .mapToDouble(x -> Math.pow(x - mean, 2))
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Вычисляет геометрическое среднее массива.
     * Основано на сумме натуральных логарифмов для избежания переполнения.
     *
     * @return геометрическое среднее, или Double.NaN, если массив пуст
     */
    public double getGeometricMean() {
        if (array.length == 0) {
            return Double.NaN;
        }

        double logSum = Arrays.stream(array)
                .mapToDouble(Math::log)
                .sum();

        return Math.exp(logSum / array.length);
    }

    /**
     * Возвращает новый массив с элементами исходного массива в случайном порядке.
     * Используется алгоритм Фишера–Йетса.
     *
     * @return перемешанный массив, или пустой массив, если исходный массив null или имеет <= 1 элемента
     */
    public int[] shuffle() {
        if (array == null || array.length <= 1) {
            return new int[0];
        }

        int[] copy = Arrays.copyOf(array, array.length);

        Random rnd = ThreadLocalRandom.current();

        for (int i = copy.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);

            int temp = copy[i];
            copy[i] = copy[j];
            copy[j] = temp;
        }

        return copy;
    }

    /**
     * Возвращает новый массив случайных элементов исходного массива.
     * Элементы могут повторяться; размер нового массива задается параметром.
     *
     * @param sampleSize размер выборки
     * @return массив случайных элементов, пустой, если исходный массив пуст или sampleSize <= 0
     */
    public int[] sample(int sampleSize) {
        if (array == null || array.length == 0 || sampleSize <= 0) {
            return new int[0];
        }

        int[] result = new int[sampleSize];
        Random rnd = ThreadLocalRandom.current();

        for (int i = 0; i < sampleSize; i++) {
            int randomIndex = rnd.nextInt(array.length);
            result[i] = array[randomIndex];
        }

        return result;
    }
}
