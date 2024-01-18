package ru.neZorinEgor.task;

import java.io.*;
import java.util.Scanner;

public class Application {
    //type counter
    static int countInt = 0;
    static int countString = 0;
    static int countFloat = 0;

    //regex
    static String intRegex = "^[+-]?\\d+$"; //ints
    static String stringRegex = "^(?![+-]?\\d)(?!\\n)[^\\d ].*$"; //stings
    static String floatRegex = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; //flots

    static PrintWriter intWriter;
    static PrintWriter stringWriter;
    static PrintWriter floatWriter;

    String prefix = "";

    static File integers = new File("integers.txt");
    static File strings = new File("strings.txt");
    static File floats = new File("floats.txt");

    public static void main(String[] args) {
        Analyst analyst = new Analyst();
        System.out.print("Enter file path: ");
        Scanner fileName = new Scanner(System.in);
        File fileNameFromScanner = new File(fileName.nextLine());
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileNameFromScanner);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found. Detail:\n" + e.getMessage());
        }
        switchFileWriter(false);

        while (scanner.hasNextLine()) {
            //обработка данных
            checkLineType(scanner.nextLine());
            }

            //удаление файла при отсутствии данных
            closeAndDeleteIfEmpty(countInt, intWriter, integers);
            closeAndDeleteIfEmpty(countString, stringWriter, strings);
            closeAndDeleteIfEmpty(countFloat, floatWriter, floats);

            //статистика вид котороый зависит от опции
            analyst.fullStatistics(integers, floats, strings, true);
            scanner.close();
        }

    //запись в файлы и сбор статистика
    public static void checkLineType(String line){
        if (line.matches(intRegex)) {
            countInt++;
            intWriter.append(line).append('\n').flush();
        }
        if (line.matches(stringRegex)) {
            countString++;
            stringWriter.append(line).append('\n').flush();
        }
        if (line.matches(floatRegex)) {
            countFloat++;
            floatWriter.append(line).append('\n').flush();
        }
    }

    //перезапись или добавление в файл
    public static void switchFileWriter(boolean action){
        try {
            intWriter = new PrintWriter(new FileWriter(integers, action));
            stringWriter = new PrintWriter(new FileWriter(strings, action));
            floatWriter = new PrintWriter(new FileWriter(floats, action));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeAndDeleteIfEmpty(int count, PrintWriter writer, File file) {
        if (count == 0) {
            writer.close();
            file.delete();
        }
    }
}