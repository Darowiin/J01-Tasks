package statistics;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {3, 1, 4, 2, 4, 1, 2, 7, 8};

        System.out.println(Arrays.toString(array));

        ArrayStatistics arrayStatistics = new ArrayStatistics(array);

        System.out.println(Arrays.toString(arrayStatistics.getMode()));

        System.out.println(arrayStatistics.getMedian());

        System.out.println(arrayStatistics.getAverage());

        System.out.println(arrayStatistics.getVariance());

        System.out.println(arrayStatistics.getGeometricMean());

        System.out.println(Arrays.toString(arrayStatistics.shuffle()));

        System.out.println(Arrays.toString(arrayStatistics.sample(15)));
    }
}
