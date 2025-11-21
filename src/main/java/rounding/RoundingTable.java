package rounding;

import java.util.List;

/**
 * Класс RoundingTable выводит таблицу со значениями округления числа
 * с использованием методов
 * {@link Math#ceil(double)},
 * {@link Math#floor(double)},
 * {@link Math#round(double)} и
 * {@link Math#rint(double)}.
 * Он формирует красивую табличную структуру с изначальными значениями и результатом каждой функции.
 */
public class RoundingTable {

    /**
     * Список значений, для которых выполняется округление.
     */
    private final List<Double> values;

    /**
     * Создаёт объект таблицы округления с указанным списком значений.
     *
     * @param values список чисел, которые необходимо вывести в таблице
     */
    public RoundingTable(List<Double> values) {
        this.values = values;
    }

    /**
     * Печатает таблицу округления в консоль.
     * Состоит из заголовка, строк значений и нижней границы.
     */
    public void print() {
        printHeader();
        for (double value : values) {
            printRow(value);
        }
        printFooter();
    }

    /**
     * Печатает верхнюю часть таблицы и заголовки столбцов.
     */
    private void printHeader() {
        System.out.println("┌────────────┬────────────┬────────────┬────────────┬────────────┐");
        System.out.printf("│ %-10s │ %-10s │ %-10s │ %-10s │ %-10s │%n",
                "Value", "ceil", "floor", "round", "rint");
        System.out.println("├────────────┼────────────┼────────────┼────────────┼────────────┤");
    }

    /**
     * Печатает одну строку таблицы с результатами вызовов методов округления.
     *
     * @param value значение, для которого выполняются операции округления
     */
    private void printRow(double value) {
        System.out.printf("│ %-10s │ %-10s │ %-10s │ %-10s │ %-10s │%n",
                value,
                Math.ceil(value),
                Math.floor(value),
                Math.round(value),
                Math.rint(value)
        );
    }

    /**
     * Печатает нижнюю рамку таблицы.
     */
    private void printFooter() {
        System.out.println("└────────────┴────────────┴────────────┴────────────┴────────────┘");
    }
}
