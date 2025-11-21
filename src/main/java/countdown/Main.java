package countdown;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();
        LocalDateTime target = null;

        if (args.length > 0) {
            target = parseArgument(String.join(" ", args));
            if (target == null) {
                System.out.println("Неверный формат аргумента. Используйте: dd.MM.yyyy HH:mm");
                return;
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите дату и время события (dd.MM.yyyy HH:mm): ");
            String line = scanner.nextLine();
            target = parseArgument(line);
            if (target == null) {
                System.out.println("Неверный формат ввода.");
                return;
            }
        }

        String result = countdown.calculateCountdown(target);
        System.out.println(result);
    }

    /**
     * Функция для парсинга строки аргумента в LocalDateTime.
     * Ожидает формат dd.MM.yyyy HH:mm.
     * @param raw исходная строка
     * @return LocalDateTime или null, если формат неверен
     */
    public static LocalDateTime parseArgument(String raw) {
        if (raw == null || raw.isBlank()) return null;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            return LocalDateTime.parse(raw.trim(), f);
        } catch (Exception e) {
            return null;
        }
    }
}
