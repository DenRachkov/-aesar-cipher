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
        String adres = scanner.nextLine();

        try {
            Path path = Path.of(adres);
            if (Files.exists(Path.of(adres))) {

                if (Files.isRegularFile(Path.of(adres))){
                    System.out.println("Файл существует");
                } else {
                    System.out.println("Файл не найден, попробуйте еще раз.");

                }
            }else {
                System.out.println("Неверный адрес файла, попробуйте еще раз.");

            }
        } catch (InvalidPathException ex) {
            System.err.println("If the path string cannot be converted to a Path");

        }
        return Path.of(adres);
    }
}

