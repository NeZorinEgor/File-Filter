package ru.neZorinEgor.task.FileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    //счетчики для проверки файла напустоту
    int intCount = 0;
    int floatCount = 0;
    int stringCount = 0;


    // Конструктор класса, принимающий флаг для записи / перезаписи
    public FileManager(boolean appendLine) {
        // Инициализация флага

        try {
            // Проверка существования файлов и их создание, если не существуют
            if (!integers.exists()) {
                integers.createNewFile();
            }
            if (!strings.exists()) {
                strings.createNewFile();
            }
            if (!floats.exists()) {
                floats.createNewFile();
            }

            // Инициализация PrintWriter с возможностью перезаписи файлов
            intWriter = new PrintWriter(new FileWriter(integers, appendLine));
            stringWriter = new PrintWriter(new FileWriter(strings, appendLine));
            floatWriter = new PrintWriter(new FileWriter(floats, appendLine));
        } catch (IOException e) {
            // Обработка ошибок при работе с файлами
            throw new RuntimeException(e);
        }
    }
    // PrintWriter для каждого типа строки
    private final PrintWriter intWriter;
    private final PrintWriter stringWriter;
    private final PrintWriter floatWriter;

    // Файлы для каждого типа строки
    private final File integers = new File("integers.txt");
    private final File strings = new File("strings.txt");
    private final File floats = new File("floats.txt");

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

    //запись в файлы и сбор статистика
    public void doFilter(String line){
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
