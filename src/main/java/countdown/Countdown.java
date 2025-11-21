package countdown;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Счётчик времени до заданного события.
 * Предоставляет метод, возвращающий строку «N дней M часов K минут» (части с 0 пропускаются)
 * либо «Уже наступило!» если момент прошёл или равен текущему.
 */
public class Countdown {
    /**
     * Вычисляет, сколько осталось до указанной даты/времени: дни, часы, минуты.
     * Если событие наступило или совпадает с текущим моментом, возвращает «Уже наступило!».
     * Формат частей: корректные окончания (1 день, 2 дня, 5 дней и т.д.).
     * @param date дата и время будущего события
     * @return строка с оставшимся временем
     */
    public String calculateCountdown(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        if (dateTime.isBefore(now) || dateTime.isEqual(now)) {
            return "Уже наступило!";
        }

        long totalMinutes = ChronoUnit.MINUTES.between(now, dateTime);
        if (totalMinutes <= 0) {
            return "Уже наступило!";
        }

        Duration duration = Duration.between(now, dateTime);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(formatUnit(days, "день", "дня", "дней"));
        if (hours > 0) {
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(formatUnit(hours, "час", "часа", "часов"));
        }
        if (minutes > 0) {
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(formatUnit(minutes, "минута", "минуты", "минут"));
        }

        if (sb.isEmpty()) {
            return "Уже наступило!";
        }
        return sb.toString();
    }

    /**
     * Подбирает корректную форму слова по числу.
     *
     * @param value число
     * @param one форма для 1 ("день")
     * @param few форма для 2–4 ("дня")
     * @param many форма для остальных ("дней")
     * @return строку "value слово"
     */
    private String formatUnit(long value, String one, String few, String many) {
        long abs = Math.abs(value);
        long lastTwoDigits = abs % 100;

        String word;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            word = many;
        } else {
            long last = abs % 10;
            if (last == 1) word = one;
            else if (last >= 2 && last <= 4) word = few;
            else word = many;
        }
        return value + " " + word;
    }
}
