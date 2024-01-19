package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;

import java.io.*;
import java.util.Scanner;

public class Application {
    static Analyst integerAnalyst = new NumericAnalyst();
    static Analyst floatAnalyst = new NumericAnalyst();
    static Analyst stringAnalyst = new StringAnalyst();
    //regex
    static String intRegex = "^[+-]?\\d+$"; //ints
    static String stringRegex = "^(?![+-]?\\d)(?!\\n)[^\\d ].*$"; //stings
    static String floatRegex = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; //flots

    static PrintWriter intWriter;
    static PrintWriter stringWriter;
    static PrintWriter floatWriter;

    static File integers = new File("integers.txt");
    static File strings = new File("strings.txt");
    static File floats = new File("floats.txt");

    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter file path: ");
            Scanner fileName = new Scanner(System.in);
            File fileNameFromScanner = new File(fileName.nextLine());
            Scanner scanner = null;
            try {
                scanner = new Scanner(fileNameFromScanner);
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            }
            //TODO засунуть в опцию
            //для опции записи | перезаписи
            switchFileWriter(false);

            try {
                while (scanner.hasNextLine()) {
                    //обработка данных
                    checkLineType(scanner.nextLine());
                }
                //опция для статистики простой | полной
                integerAnalyst.doAnalysis(integers, true);
                floatAnalyst.doAnalysis(floats, true);
                stringAnalyst.doAnalysis(strings, true);
                scanner.close();

                //удаление файла при отсутствии данных
                closeAndDeleteIfEmpty(integerAnalyst.getLineCount(), intWriter, integers);
                closeAndDeleteIfEmpty(floatAnalyst.getLineCount(), floatWriter, floats);
                closeAndDeleteIfEmpty(stringAnalyst.getLineCount(), stringWriter, strings);
            } catch (NullPointerException e) {
                System.out.println("Убедитесь в наименовании файла");
            }
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

    //запись в файлы и сбор статистика
    public static void checkLineType(String line){
        if (line.matches(intRegex)) {
            intWriter.append(line).append('\n').flush();
        }
        if (line.matches(stringRegex)) {
            stringWriter.append(line).append('\n').flush();
        }
        if (line.matches(floatRegex)) {
            floatWriter.append(line).append('\n').flush();
        }
    }

    public static void closeAndDeleteIfEmpty(long count, PrintWriter writer, File file) {
        if (count == 0) {
            writer.close();
            file.delete();
        }
    }
}