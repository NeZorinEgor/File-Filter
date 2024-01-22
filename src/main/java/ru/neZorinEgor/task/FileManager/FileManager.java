package ru.neZorinEgor.task.FileManager;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    //счетчики для проверки файла на пустоту
    int intCount = 0;
    int floatCount = 0;
    int stringCount = 0;

    private String path;
    private String prefix;

    // Конструктор класса, принимающий флаг для записи / перезаписи
    public FileManager(boolean appendLine, String path, String prefix) {
        this.path = path;
        this.prefix = prefix;
        // Инициализация флага

        try {
            String intFileName = path + File.separator + prefix + "integers.txt";
            String strFileName = path + File.separator + prefix + "strings.txt";
            String floatFileName = path + File.separator + prefix + "floats.txt";

            // Файлы для каждого типа строки
            integers = new File(intFileName);
            strings = new File(strFileName);
            floats = new File(floatFileName);

            // Инициализация PrintWriter с возможностью перезаписи файлов
            intWriter = new PrintWriter(new FileWriter(integers, appendLine));
            stringWriter = new PrintWriter(new FileWriter(strings, appendLine));
            floatWriter = new PrintWriter(new FileWriter(floats, appendLine));
        } catch (IOException e) {
            // Обработка ошибок при работе с файлами
            System.out.println(e.getMessage() + "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            throw new RuntimeException(e);
        }
    }
    // PrintWriter для каждого типа строки
    private final PrintWriter intWriter;
    private final PrintWriter stringWriter;
    private final PrintWriter floatWriter;

    String intFileName = path + prefix + "integers.txt";
    String strFileName = path + prefix + "strings.txt";
    String floatFileName = path + prefix + "floats.txt";

    // Файлы для каждого типа строки
    private File integers = new File(intFileName);
    private File strings = new File(strFileName);
    private File floats = new File(floatFileName);

    public String getIntRegex() {
        // Регулярные выражения для фильтрации строк
        // целые числа
        return "^[+-]?\\d+$";
    }

    public String getStringRegex() {
        // строки
        return "^(?![+-]?\\d)(?!\\n)[^\\d ].*$";
    }

    public String getFloatRegex() {
        // числа с плавающей точкой
        return "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?";
    }

    public PrintWriter getIntWriter() {
        return intWriter;
    }

    public PrintWriter getStringWriter() {
        return stringWriter;
    }

    public PrintWriter getFloatWriter() {
        return floatWriter;
    }

    public File getIntegersFile() {
        return integers;
    }

    public File getStringsFile() {
        return strings;
    }

    public File getFloatsFile() {
        return floats;
    }

    public void doFilterFile(File file){
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                doFilterLine(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            //отладка сценария
            System.out.println("Error: File not found: " + e.getMessage());
            System.out.println("Please make sure you entered the correct file name and try again.");
            System.exit(130);
        }
    }

    //запись в файлы и сбор статистика
    public void doFilterLine(String line){
        if (line.matches(getIntRegex())) {
            intCount++;
            getIntWriter().append(line).append('\n').flush();
        }
        if (line.matches(getStringRegex())) {
            stringCount++;
            getStringWriter().append(line).append('\n').flush();
        }
        if (line.matches(getFloatRegex())) {
            floatCount++;
            getFloatWriter().append(line).append('\n').flush();
        }
    }

    public void checkAndDelete(int counter,
                               PrintWriter writer,
                               File file)
    {
        if (counter == 0) {
            writer.close();
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void deleteFileIfEmpty() {
        checkAndDelete(intCount, getIntWriter(), integers);
        checkAndDelete(stringCount, getStringWriter(), strings);
        checkAndDelete(floatCount, getFloatWriter(), floats);
    }

}
