package ru.javarush.drachkov.caesarcipher;


import com.sun.source.tree.BreakTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;


public class Main {
    private static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', 'ю', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', 'А', 'Б', 'В', 'Г', 'Д',
            'Е', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');


    public static void main(String[] args)  {
        System.out.println("Шифр Цезаря\n" + "Введите цифру 1 - если вы хотите зашифровать текст\n" +
                "Введите цифру 2 - если вы хотите расшифровать текст\n" +
                "Введите цифру 3 - если вы хотите расшифровать текс методом Brute Force");

        checkingMainMenu();


    }

    public static void checkingMainMenu() {

        Scanner scanner = new Scanner(System.in);
        int num = 0;
        try {
            num = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("Введено не число, повторите еще раз.");
            checkingMainMenu();
        }

        switch (num) {
            case 1:
                fileRecordingEncryption();

                break;
            case 2:
                fileRecordingDecryption();

                break;
            case 3:
                System.out.println("Данная опция временно в разработке\n" + "Приходите позднее.");
                break;

            default:
                System.out.println("Вы ввели не то число, повторите еще раз.");
                checkingMainMenu();


        }
    }

    public static Path checkingSourceText() {
        System.out.println("Введите адрес файла с исходным текстом");

        Scanner scanner = new Scanner(System.in);
        String adres;
        do {
            adres = scanner.nextLine();


            try {

                if (Files.exists(Path.of(adres))) {

                    if (Files.isRegularFile(Path.of(adres))) {
                        System.out.println("Адрес принят");
                    } else {
                        System.out.println("Файл не найден, попробуйте еще раз:");
                        checkingSourceText();

                    }
                } else {
                    System.out.println("Неверный адрес файла, попробуйте еще раз:");
                    checkingSourceText();

                }
            } catch (InvalidPathException ex) {
                System.err.println("Такого файла не существует" + ex.getMessage());
            } catch (SecurityException ex) {
                System.err.println("Это не конечный файл" + ex.getMessage());
            }
        } while (!Files.exists(Path.of(adres)) && Files.isRegularFile(Path.of(adres)));
        return Path.of(adres);
    }

    public static int checkingKey() {

        System.out.println("Введите ключ от 1 до " + ALPHABET.size());
        Scanner scanner = new Scanner(System.in);
        int key = 0;
        try {
            key = scanner.nextInt();
            if (key > 0 & key <= ALPHABET.size()) {
                System.out.println("Ключ принят");
            } else {
                System.out.println("Вы ввели несоответствующий ключ, повторите попытку:");
                checkingKey();
            }
        } catch (InputMismatchException ex) {
            System.err.println("Вы ввели не число, повторите попытку: " );
            checkingKey();

        }
        return key;
    }

    public static Path checkingResultText() {
        System.out.println("Введите адрес файла, в который нужно записать результат.");
        Scanner scanner = new Scanner(System.in);
        String result;

        do {
            result = scanner.nextLine();
            try {
                Path path = Path.of(result);
                if (Files.exists(Path.of(result))) {
                    if (Files.isRegularFile(Path.of(result))) {
                        System.out.println("Данные приняты.");


                    } else {
                        System.out.println("Файл не найден, попробуйте еще раз");
                        checkingResultText();
                    }
                } else {
                    System.out.println("Неверный адрес файла, попробуйте еще раз");
                    checkingResultText();
                }
            } catch (InvalidPathException ex) {
                System.err.println("Такого файла не существует" + ex.getMessage());
            } catch (SecurityException ex) {
                System.err.println("Это не конечный файл" + ex.getMessage());
            }
        } while (!Files.exists(Path.of(result)) && Files.isRegularFile(Path.of(result)));

        return Path.of(result);

    }

    public static List<String> fileReading(Path path) {

        List<String> list = new ArrayList<>();

        try {
            list = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Ошибка чтения входящего файла" + e.getMessage());
        }
        return list;
    }

    public static void fileRecordingEncryption() {
        List<String> newList = new ArrayList<>();
        int key = checkingKey();
        for (String str : fileReading(checkingSourceText())) {
            newList.add(encryption(str, key));
        }
        try {
            Files.write(checkingResultText(), newList);
        }catch (IOException e) {
            System.err.println("Ошибка записи файлов" + e.getMessage());
        }
        System.out.println("Готово. Всего хорошего!");
    }

    public static void fileRecordingDecryption() {
        List<String> newList = new ArrayList<>();
        int key = checkingKey();
        for (String str : fileReading(checkingSourceText())) {
            newList.add(decryption(str,key));
        }
        try {
            Files.write(checkingResultText(),newList);
        }catch (IOException e) {
            System.err.println("Ошибка записи файлов" + e.getMessage());
        }
        System.out.println("Готово. Всего хорошего!");
    }

    public static String encryption(String str, int key) {

        StringBuilder result = new StringBuilder();

        for (char text : str.toCharArray()) {

            if (ALPHABET.contains(text)) {

                char newChars = ALPHABET.get((ALPHABET.indexOf(text) + key + ALPHABET.size()) % ALPHABET.size());

                result.append(newChars);

            } else {
                result.append(text);
            }
        }
        return result.toString();
    }

    public static String decryption(String str, int key) {

        StringBuilder result = new StringBuilder();

        for (char text : str.toCharArray()) {

            if (ALPHABET.contains(text)) {
                char newChars = ALPHABET.get((ALPHABET.indexOf(text) - key + ALPHABET.size()) % ALPHABET.size());
                result.append(newChars);

            } else {
                result.append(text);
            }
        }
        return result.toString();
    }
}













