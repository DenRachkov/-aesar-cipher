package ru.javarush.drachkov.caesarcipher;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {


    public static void main(String[] args) {
        System.out.println("Шифр Цезаря\n" + "Введите цифру 1 - если вы хотите зашифровать текст\n" +
                "Введите цифру 2 - если вы хотите расшифровать текст\n" +
                "Введите цифру 3 - если вы хотите расшифровать текс методом Brute Force");

        checkingMainMenu();



    }
    public static void checkingMainMenu () {
        final String  SOURCE_TEXT = "Введите адрес файла с исходным текстом";

        Scanner scanner = new Scanner(System.in);
        int num = 0;
        try {
            num = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Введено не число, повторите еще раз.");
            checkingMainMenu();
        }

        switch (num) {
            case 1:
                System.out.println(SOURCE_TEXT);
                checkingSourceText();

                break;
            case 2:
                System.out.println(SOURCE_TEXT);
                checkingSourceText();

                break;
            case 3:
                System.out.println(SOURCE_TEXT );
                checkingSourceText();
                break;

            default:
                System.out.println("Вы ввели не то число, повторите еще раз.");
                checkingMainMenu();


        }
    }
    public static Path checkingSourceText () {

        Scanner scanner = new Scanner(System.in);
        String adres;
        do {
            adres = scanner.nextLine();


            try {
                Path path = Path.of(adres);
                if (Files.exists(Path.of(adres))) {

                    if (Files.isRegularFile(Path.of(adres))){
                        System.out.println("Введите ключ");
                    } else {
                        System.out.println("Файл не найден, попробуйте еще раз.");
                        checkingSourceText();

                    }
                }else {
                    System.out.println("Неверный адрес файла, попробуйте еще раз.");
                    checkingSourceText();

                }
            } catch (InvalidPathException ex) {
                System.err.println("If the path string cannot be converted to a Path");
            } catch (SecurityException ex) {
                System.err.println("In the case of the default provider, " +
                        "and a security manager is installed," +
                        " its checkRead method denies read access to the file");
            }
        } while (!Files.exists(Path.of(adres)) && Files.isRegularFile(Path.of(adres)));
        return Path.of(adres);
    }
    public static int checkingKey () {

        Scanner scanner = new Scanner(System.in);
        int key = 0;
        try {
            key = scanner.nextInt();
            if (key >= 0 & key <= 26) {
                System.out.println("Введите адрес файла, в который нужно записать результат.");
            }else {
                System.out.println("Вы ввели несоответствующий ключ, повторите попытку:");
                checkingKey();
            }
        } catch (InputMismatchException ex){
            System.err.println("Вы ввели не число, повторите попытку: ");
            checkingKey();

        }
        return key;
    }
}

