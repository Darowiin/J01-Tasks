package months;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MonthInfo[] dates = new MonthInfo[3];

        dates[0] = new MonthInfo(LocalDate.parse("2023-11-12"));
        dates[1] = new MonthInfo(LocalDate.parse("1900-01-01"));
        dates[2] = new MonthInfo(LocalDate.parse("2020-02-12"));

        StringBuilder sb = new StringBuilder();
        for (MonthInfo date : dates) {
            sb.append(String.format("[%s (%d): 1-й день=%s, последний=%s, дней=%d, %s];",
                    date.getMonthName(),
                    date.getMonthValue(),
                    date.getFirstDayOfMonthInWeek(),
                    date.getLastDayDate(),
                    date.getLengthOfMonth(),
                    date.getYearWithQuarter()
            ));
        }
        System.out.println(sb);
    }
}
