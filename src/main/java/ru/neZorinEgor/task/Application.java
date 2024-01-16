package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    //регулярные выражения для строки файла
    static String isInt = "^[+-]?\\d+$"; //простые числа
    static String isString = "^(?![+-]?\\d)(?!\\n)[^\\d ].*$"; //слова
    static String isFloat = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; //числа с плаваюзей запятой

    public static void main(String[] args) throws FileNotFoundException {
        var file = new File("src/main/java/ru/neZorinEgor/task/data/input/input.txt");
        int countLines = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            System.out.println(checkLineType(scanner.nextLine()));
            countLines++;
        }
        scanner.close();
    }
    public static String checkLineType(String line){
        if(line.matches(isInt)) {
            return "int: " + line;
        }
        if(line.matches(isString)) {
            return "string: " + line;
        }
        if (line.matches(isFloat)) {
            return "float: " + line;
        }
        return "?: "+ line;
    }
}
