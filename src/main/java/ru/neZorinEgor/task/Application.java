package ru.neZorinEgor.task;

import ru.neZorinEgor.task.Analysts.Analyst;
import ru.neZorinEgor.task.Analysts.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analysts.analysisImpl.StringAnalyst;

import java.io.*;
import java.util.Scanner;

public class Application {

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
        Analyst analyst1 = new NumericAnalyst();
        Analyst analyst2 = new NumericAnalyst();
        Analyst analyst3 = new StringAnalyst();

        System.out.print("Enter file path: ");
        Scanner fileName = new Scanner(System.in);
        File fileNameFromScanner = new File(fileName.nextLine());
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileNameFromScanner);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found. Detail:\n" + e.getMessage());
        }
        //для опции записи | перезаписи
        switchFileWriter(false);

        while (scanner.hasNextLine()) {
            //обработка данных
            checkLineType(scanner.nextLine());
            }

            //опция для статистики простой | полной
            analyst1.collectAnalysis(integers, true);
            analyst2.collectAnalysis(floats, true);
            analyst3.collectAnalysis(strings, true);
            scanner.close();

            //удаление файла при отсутствии данных
            closeAndDeleteIfEmpty(analyst1.getLineCount(), intWriter, integers);
            closeAndDeleteIfEmpty(analyst2.getLineCount(), floatWriter, floats);
            closeAndDeleteIfEmpty(analyst3.getLineCount(), stringWriter, strings);



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

    public static void closeAndDeleteIfEmpty(long count, PrintWriter writer, File file) {
        if (count == 0) {
            writer.close();
            file.delete();
        }
    }
}