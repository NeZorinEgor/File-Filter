package ru.neZorinEgor.task;

import java.io.*;
import java.util.Scanner;

public class Application {
    //регулярные выражения
    static String isInt = "^[+-]?\\d+$"; //простые числа
    static String isString = "^(?![+-]?\\d)(?!\\n)[^\\d ].*$"; //слова
    static String isFloat = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; //числа с плаваюзей запятой

    static int countInt = 0;
    static int cointString = 0;
    static int cointFloat = 0;

    public static void main(String[] args) throws IOException {
        var file = new File("src/main/java/ru/neZorinEgor/task/data/input/input.txt");
        int countLines = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            checkLineType(scanner.nextLine());
            countLines++;
        }
        System.out.println("int: " + countInt);
        System.out.println("string: " + cointString);
        System.out.println("float: " + cointFloat);
        scanner.close();


    }
    public static void checkLineType(String line) throws IOException {
        FileWriter intWriter = new FileWriter("src/main/java/ru/neZorinEgor/task/data/output/integers.txt", true);
        FileWriter stringWriter = new FileWriter("src/main/java/ru/neZorinEgor/task/data/output/strings.txt", true);
        FileWriter floatWriter = new FileWriter("src/main/java/ru/neZorinEgor/task/data/output/floats.txt", true);

        if(line.matches(isInt)) {
            countInt++;
            intWriter.append(line).append('\n');
            intWriter.close();
        }
        if(line.matches(isString)) {
            cointString++;
            stringWriter.append(line).append('\n');
            stringWriter.close();
        }
        if (line.matches(isFloat)) {
            cointFloat++;
            floatWriter.append(line).append('\n');
            floatWriter.close();
        }
    }
}
