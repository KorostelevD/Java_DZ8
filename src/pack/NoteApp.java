package pack;

import java.io.IOException; //використовується для обробки помилок вводу/виводу, зокрема при роботі з файлами.
import java.nio.charset.Charset; //дозволяє задавати кодування символів для читання і запису текстових даних.
import java.nio.file.*; // класи з пакету java.nio.file, які використовуються для роботи з файловою системою: Path, Paths, Files, і StandardOpenOption.
import java.time.LocalDateTime; //дозволяє отримувати поточний дату та час.
import java.time.format.DateTimeFormatter; //дозволяє форматувати дату і час у потрібному форматі.
import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private static final Charset CHARSET = Charset.forName("cp1251"); //кирилиця
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення адреси або імені файлу
        System.out.print("Введіть адресу або ім'я файлу: ");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        // Перевірка існування файлу, створення нового, якщо не існує
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("Файл створено: " + path.toString());
            } catch (IOException e) {
                System.err.println("Помилка створення файлу: " + e.getMessage());
                return;
            }
        }

        // Основний цикл роботи програми
        while (true) {
            System.out.print("Введіть нотатку або команду (/r - для виведення нотаток, /exit - для виходу): ");
            String input = scanner.nextLine();

            if ("/r".equals(input)) {
                // Читання і виведення всіх нотаток з файлу
                try {
                    List<String> notes = Files.readAllLines(path, CHARSET);
                    System.out.println("Всі нотатки:");
                    for (String note : notes) {
                        System.out.println(note);
                    }
                } catch (IOException e) {
                    System.err.println("Помилка читання файлу: " + e.getMessage());
                }
            } else if ("/exit".equals(input)) {
                // Вихід з програми
                System.out.println("Програма завершила роботу.");
                break;
            } else {
                // Додавання нової нотатки з датою і часом
                String timestamp = LocalDateTime.now().format(FORMATTER);
                String note = timestamp + " - " + input;

                try {
                    Files.write(path, (note + System.lineSeparator()).getBytes(CHARSET), StandardOpenOption.APPEND);
                    System.out.println("Нотатку додано.");
                } catch (IOException e) {
                    System.err.println("Помилка запису у файл: " + e.getMessage());
                }
            }
        }
    }
}