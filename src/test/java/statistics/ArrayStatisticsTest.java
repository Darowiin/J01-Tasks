package statistics;

import org.junit.jupiter.api.Test;
import utils.RandomIntGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsTest {

    @Test
    void testEmptyArray() {
        int[] empty = {};
        ArrayStatistics stats = new ArrayStatistics(empty);

        assertEquals(Double.NaN, stats.getMedian());
        assertEquals(Double.NaN, stats.getAverage());
        assertEquals(Double.NaN, stats.getVariance());
        assertEquals(Double.NaN, stats.getGeometricMean());
        assertArrayEquals(new int[0], stats.getMode());
        assertArrayEquals(new int[0], stats.shuffle());
        assertArrayEquals(new int[0], stats.sample(5));
    }

    @Test
    void testSingleElementArray() {
        int[] single = {42};
        ArrayStatistics stats = new ArrayStatistics(single);

        assertEquals(42, stats.getMedian());
        assertEquals(42.0, stats.getAverage());
        assertEquals(0.0, stats.getVariance(), 1e-6);
        assertEquals(42.0, stats.getGeometricMean(), 1e-6);
        assertArrayEquals(new int[]{42}, stats.getMode());

        int[] shuffled = stats.shuffle();
        assertEquals(0, shuffled.length);

        int[] sample = stats.sample(3);
        assertEquals(3, sample.length);
        for (int val : sample) assertEquals(42, val);
    }

    @Test
    void testThreeElementArray() {
        int[] arr = {1, 2, 3};
        ArrayStatistics stats = new ArrayStatistics(arr);

        assertEquals(2, stats.getMedian());
        assertEquals(2.0, stats.getAverage());
        assertEquals(0.666666666, stats.getVariance(), 1e-6);
        assertEquals(Math.cbrt(1 * 2 * 3), stats.getGeometricMean(), 1e-6);
        assertArrayEquals(new int[] { 1, 2, 3 }, stats.getMode());

        int[] shuffled = stats.shuffle();
        Set<Integer> shuffledSet = new HashSet<>();
        for (int val : shuffled) shuffledSet.add(val);
        assertEquals(new HashSet<>(Arrays.asList(1,2,3)), shuffledSet);

        int[] sample = stats.sample(5);
        assertEquals(5, sample.length);
        for (int val : sample) assertTrue(val >= 1 && val <= 3);
    }

    @Test
    void testManualTenElementArray() {
        int[] arr = {10,20,30,40,50,60,70,80,90,100};
        ArrayStatistics stats = new ArrayStatistics(arr);

        assertEquals(55.0, stats.getMedian());
        assertEquals(55.0, stats.getAverage());

        List<Integer> expected = Arrays.asList(10,20,30,40,50,60,70,80,90,100);
        List<Integer> actual = Arrays.stream(stats.getMode()).boxed().toList();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));

        int[] shuffled = stats.shuffle();
        assertEquals(10, shuffled.length);
        Set<Integer> shuffledSet = new HashSet<>();
        for (int val : shuffled) shuffledSet.add(val);
        assertEquals(new HashSet<>(Arrays.asList(10,20,30,40,50,60,70,80,90,100)), shuffledSet);

        int[] shuffled2 = stats.shuffle();
        assertFalse(Arrays.equals(shuffled, shuffled2));

        int[] sample = stats.sample(15);
        assertEquals(15, sample.length);
        Set<Integer> arrSet = new HashSet<>();
        for (int val : arr) arrSet.add(val);
        for (int val : sample) assertTrue(arrSet.contains(val));
    }

    @Test
    void testLargeArrayWithSeed() {
        RandomIntGenerator generator = new RandomIntGenerator(12345L);
        int[] largeArray = generator.generateDefaultRange(100_000);
        ArrayStatistics stats = new ArrayStatistics(largeArray);

        int[] shuffled = stats.shuffle();
        assertEquals(100_000, shuffled.length);
        Set<Integer> originalSet = new HashSet<>();
        for (int val : largeArray) originalSet.add(val);
        Set<Integer> shuffledSet = new HashSet<>();
        for (int val : shuffled) shuffledSet.add(val);
        assertEquals(originalSet, shuffledSet);

        int[] shuffled2 = stats.shuffle();
        assertFalse(Arrays.equals(shuffled, shuffled2));

        int[] sample = stats.sample(120_000);
        assertEquals(120_000, sample.length);
        for (int val : sample) assertTrue(originalSet.contains(val));
    }
}
