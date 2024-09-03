package pack;

import java.io.*; //: Імпортує всі класи з пакету java.io, включаючи IOException, BufferedOutputStream, FileOutputStream, PrintStream, BufferedReader, і FileReader, які використовуються для роботи з введенням-виведенням.
import java.util.Random;

public class Main {

    private static final int NUMBER_OF_RANDOM_CHARS = 100; // Кількість випадкових символів, які будуть записані у файл.
    private static final String FILE_NAME = "random_chars.txt"; //Назва файлу, куди буде записано випадкові символи.

    public static void main(String[] args) {

        try {
            // Запис у файл випадкових символів
            writeRandomCharsToFile(FILE_NAME);
            // Читання з файлу та виведення вмісту на консоль
            readFileContent(FILE_NAME);
        } catch (IOException e) {
            System.err.println("Помилка при роботі з файлом: " + e.getMessage());
        }
    }
        //метод, який записує випадкові символи у файл з використанням буферизованих потоків.
    private static void writeRandomCharsToFile(String fileName) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
             PrintStream ps = new PrintStream(bos)) {

            Random random = new Random();
            for (int i = 0; i < NUMBER_OF_RANDOM_CHARS; i++) {
                // Генеруємо випадковий символ (від A до Z)
                char randomChar = (char) (random.nextInt(26) + 'A');
                // Записуємо символ з пробілом
                ps.print(randomChar + " ");
            }
            System.out.println("Випадкові символи з пробілами успішно записані у файл: " + fileName);
        }
    }

    //  Вивід даних з файлу в консоль
    private static void readFileContent(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Вміст файлу:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

    }
}