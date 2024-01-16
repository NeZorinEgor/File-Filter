package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Application {

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
        //регулярные выражения
        String isInt = "[+-]?\\d+"; //только для чисел без точки
        String isString = "[а-яА-Яa-zA-Z][^.0-9\\r\\n]+.[\\D](?![\\r\\n])"; //только для слов
        String isFloat = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; //только числа с плавающей точкой

        if(line.matches(isInt))
            return "int";
        if(line.matches(isString))
            return "string";
        if (line.matches(isFloat))
            return "float";
        return "?";
    }
}
