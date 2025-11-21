package rounding;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        var values = Arrays.asList(
                30.0, 10000.1, 12.5, 99.99, 0.0, -23.45, -4.5, -129.675
        );

        RoundingTable table = new RoundingTable(values);
        table.print();
    }
}
