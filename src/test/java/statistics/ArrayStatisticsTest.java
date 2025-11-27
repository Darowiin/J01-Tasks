package statistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.RandomIntGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsTest {
    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("Крайний случай: Пустой массив")
    void testEmptyArray() {
        int[] emptyArray = {};
        ArrayStatistics stats = new ArrayStatistics(emptyArray);

        assertEquals(Double.NaN, stats.getMedian(), "Медиана пустого массива должна быть NaN");
        assertEquals(Double.NaN, stats.getAverage(), "Среднее пустого массива должно быть NaN");
        assertEquals(Double.NaN, stats.getVariance(), "Дисперсия пустого массива должна быть NaN");
        assertEquals(Double.NaN, stats.getGeometricMean(), "Среднее геометрическое должно быть NaN");

        assertArrayEquals(new int[0], stats.getMode(), "Мода должна быть пустым массивом");
        assertArrayEquals(new int[0], stats.shuffle(), "Перемешивание должно вернуть пустой массив");
        assertArrayEquals(new int[0], stats.sample(5), "Выборка должна вернуть пустой массив");
    }

    @Test
    @DisplayName("Крайний случай: Массив из одного элемента")
    void testSingleElementArray() {
        int expectedValue = 42;
        int[] singleElementArray = {expectedValue};
        ArrayStatistics stats = new ArrayStatistics(singleElementArray);

        assertEquals(expectedValue, stats.getMedian(), "Медиана должна быть равна значению");
        assertEquals(expectedValue, stats.getAverage(), DELTA, "Среднее должно быть равно значению");
        assertEquals(0.0, stats.getVariance(), DELTA, "Дисперсия должна быть 0.0");
        assertEquals(expectedValue, stats.getGeometricMean(), DELTA, "Среднее геометрическое должно быть равно значению");
        assertArrayEquals(new int[]{expectedValue}, stats.getMode(), "Мода должна быть массивом с единственным элементом");

        int[] shuffled = stats.shuffle();
        assertEquals(0, shuffled.length, "Перемешивание массива из одного элемента должно вернуть массив длины 0");

        int sampleSize = 3;
        int[] sample = stats.sample(sampleSize);
        assertEquals(sampleSize, sample.length, "Длина выборки должна быть 3");
        for (int val : sample) {
            assertEquals(expectedValue, val, "Каждый элемент выборки должен быть равен 42");
        }
    }

    @Test
    @DisplayName("Небольшой массив {1, 2, 3}")
    void testThreeElementArray() {
        int[] data = {1, 2, 3};
        ArrayStatistics stats = new ArrayStatistics(data);
        List<Integer> expectedElements = Arrays.asList(1, 2, 3);

        assertEquals(2, stats.getMedian(), "Медиана должна быть 2");
        assertEquals(2.0, stats.getAverage(), DELTA, "Среднее должно быть 2.0");
        assertEquals(0.666666666, stats.getVariance(), DELTA, "Дисперсия должна быть ~0.667");
        assertEquals(Math.cbrt(6.0), stats.getGeometricMean(), DELTA, "Среднее геометрическое должно быть куб. корень из 6");

        Set<Integer> actualMode = Arrays.stream(stats.getMode()).boxed().collect(Collectors.toSet());
        assertEquals(new HashSet<>(expectedElements), actualMode, "Мода: все элементы уникальны, набор должен совпадать");

        int[] shuffled = stats.shuffle();
        assertEquals(data.length, shuffled.length, "Длина перемешанного массива должна быть 3");
        Set<Integer> shuffledSet = Arrays.stream(shuffled).boxed().collect(Collectors.toSet());
        assertEquals(new HashSet<>(expectedElements), shuffledSet, "Состав элементов после shuffle должен сохраниться");

        int sampleSize = 5;
        int[] sample = stats.sample(sampleSize);
        assertEquals(sampleSize, sample.length, "Длина выборки должна быть 5");
        for (int val : sample) {
            assertTrue(val >= 1 && val <= 3, "Элемент выборки должен быть в диапазоне [1; 3]");
        }
    }

    @Test
    @DisplayName("Массив из 10 элементов {10..100}")
    void testManualTenElementArray() {
        int[] data = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        ArrayStatistics stats = new ArrayStatistics(data);
        Set<Integer> originalSet = Arrays.stream(data).boxed().collect(Collectors.toSet());

        assertEquals(55.0, stats.getMedian(), DELTA, "Медиана должна быть 55.0");
        assertEquals(55.0, stats.getAverage(), DELTA, "Среднее должно быть 55.0");

        Set<Integer> actualMode = Arrays.stream(stats.getMode()).boxed().collect(Collectors.toSet());
        assertEquals(originalSet, actualMode, "Мода: все элементы уникальны, набор должен совпадать");

        int[] shuffled1 = stats.shuffle();
        int[] shuffled2 = stats.shuffle();

        assertEquals(data.length, shuffled1.length, "Длина shuffled1 должна быть 10");
        Set<Integer> shuffledSet = Arrays.stream(shuffled1).boxed().collect(Collectors.toSet());
        assertEquals(originalSet, shuffledSet, "Состав элементов после shuffle должен сохраниться");

        assertFalse(Arrays.equals(shuffled1, shuffled2), "Два последовательных Shuffle должны быть разными");

        int sampleSize = 15;
        int[] sample = stats.sample(sampleSize);
        assertEquals(sampleSize, sample.length, "Длина выборки должна быть 15");
        for (int val : sample) {
            assertTrue(originalSet.contains(val), "Элементы выборки должны быть из исходного набора");
        }
    }

    @Test
    @DisplayName("Большой массив: Проверка Shuffle и Sample")
    void testLargeArrayWithSeed() {
        RandomIntGenerator generator = new RandomIntGenerator(12345L);
        int arraySize = 100_000;
        int sampleSize = 120_000;
        int[] largeArray = generator.generateDefaultRange(arraySize);
        ArrayStatistics stats = new ArrayStatistics(largeArray);
        Set<Integer> originalSet = Arrays.stream(largeArray).boxed().collect(Collectors.toSet());

        int[] shuffled1 = stats.shuffle();
        int[] shuffled2 = stats.shuffle();

        assertEquals(arraySize, shuffled1.length, "Длина shuffled1 должна быть 100 000");
        Set<Integer> shuffledSet = Arrays.stream(shuffled1).boxed().collect(Collectors.toSet());
        assertEquals(originalSet, shuffledSet, "Состав элементов после shuffle должен сохраниться");

        assertFalse(Arrays.equals(shuffled1, shuffled2), "Shuffle должен быть случайным и отличаться от предыдущего");

        int[] sample = stats.sample(sampleSize);
        assertEquals(sampleSize, sample.length, "Длина выборки должна быть 120 000");
        for (int val : sample) {
            assertTrue(originalSet.contains(val), "Элементы выборки должны быть из исходного набора");
        }
    }
}
