package signs;

/**
 * Класс для анализа последовательностей по знаку.
 * Предоставляет метод для вычисления максимальной длины серии подряд идущих
 * чисел одного знака в целочисленном массиве.
 */
public class SignSeriesCounter {
    /**
     * Вычисляет длину самой длинной серии подряд идущих чисел одного знака в массиве.
     *
     * @param array входной массив целых чисел; допускается {@code null}
     * @return длина максимальной серии знакоповторений; для {@code null} или пустого массива возвращается 0
     */
    public static int maxSignRunLength(int[] array) {
        if (array == null || array.length == 0) return 0;

        int maxLength = 0;
        int currentLength = 0;
        int currentSign = 0;

        for (int v : array) {
            int s = Integer.compare(v, 0);

            if (currentLength == 0) {
                currentLength = 1;
                currentSign = s;
            } else if (s == currentSign) {
                currentLength++;
            } else {
                if (currentLength > maxLength) maxLength = currentLength;
                currentLength = 1;
                currentSign = s;
            }
        }

        if (currentLength > maxLength) maxLength = currentLength;
        return maxLength;
    }
}
