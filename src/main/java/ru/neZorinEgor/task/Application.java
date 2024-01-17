package ru.neZorinEgor.task;

import ru.neZorinEgor.task.CastomErrors.CastomFileNotFoundException;

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

    static File integers = new File("integers.txt");
    static File strings = new File("strings.txt");
    static File floats = new File("floats.txt");

    public static void main(String[] args) throws FileNotFoundException {
        while(true) {
            System.out.print("Enter file path: ");
            Scanner fileName = new Scanner(System.in);
            File fileNameFromScanner = new File(fileName.nextLine());

            Scanner scanner;
            try {
                scanner = new Scanner(fileNameFromScanner);
            } catch (FileNotFoundException e) {
                throw new CastomFileNotFoundException("Error: file not found. Detail:\n" + e.getMessage());
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

            //краткая статистика
            briefStatistics();
            scanner.close();
        }
    }

    //запись в файлы и сбор статистика
    public static void checkLineType(String line){
        if (line.matches(intRegex)) {
            countInt++;
            intWriter.append(line).append(String.valueOf('\n'));
            intWriter.flush();
        }
        if (line.matches(stringRegex)) {
            countString++;
            stringWriter.append(line).append(String.valueOf('\n'));
            stringWriter.flush();
        }
        if (line.matches(floatRegex)) {
            countFloat++;
            floatWriter.append(line).append(String.valueOf('\n'));
            floatWriter.flush();
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

    //TODO: реализовать корректный подсчет в бесконечной итерации, руфакторинг, разделение логики
    //краткая статистика
    public static  void briefStatistics(){
        System.out.println("Rest statistic: \n\t├──return");
        System.out.println("\t│\t├─ Integer variable:\t" + countInt);
        System.out.println("\t│\t├── String variable:\t" + countString);
        System.out.println("\t│\t└─── Float variable:\t" + countFloat);
        System.out.println("\t└──directory");
        System.out.println("\t\t└─ Path:" + (integers.getAbsolutePath()));
        System.out.println("End.");
    }
}