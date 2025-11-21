package powers;

/**
 * Класс для вычисления количества десятичных цифр числа a^n,
 * не вычисляя само число напрямую.
 */
public class PowerDigitsCalculator {

    /**
     * Возвращает количество десятичных цифр в числе a^n.
     *
     * @param a основание, 0 < a <= 2^32
     * @param n показатель степени, 0 <= n <= 1000
     * @return количество цифр
     */
    public int countDigits(long a, int n) {
        if (n == 0) {
            return 1;
        }

        double digits = n * Math.log10(a);
        return (int) Math.floor(digits) + 1;
    }
}
