package ru.neZorinEgor.task.FileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
    // Регулярные выражения для фильтрации строк
    private final String intRegex = "^[+-]?\\d+$"; // целые числа
    private final String stringRegex = "^(?![+-]?\\d)(?!\\n)[^\\d ].*$"; // строки
    private final String floatRegex = "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?"; // числа с плавающей точкой

    int intCount = 0;
    int floatCount = 0;
    int stringCount = 0;

    // Флаг для определения, нужно ли перезаписывать файлы
    private boolean switchFileWriter;

    // PrintWriter для каждого типа строки
    private final PrintWriter intWriter;
    private final PrintWriter stringWriter;
    private final PrintWriter floatWriter;

    // Файлы для каждого типа строки
    private File integers = new File("integers.txt");
    private File strings = new File("strings.txt");
    private File floats = new File("floats.txt");

    public String getIntRegex() {
        return intRegex;
    }

    public String getStringRegex() {
        return stringRegex;
    }

    public String getFloatRegex() {
        return floatRegex;
    }

    public boolean isSwitchFileWriter() {
        return switchFileWriter;
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

    public File getIntegers() {
        return integers;
    }

    public File getStrings() {
        return strings;
    }

    public File getFloats() {
        return floats;
    }




    // Конструктор класса, принимающий флаг switchFileWriter
    public FileManager(boolean switchFileWriter) {
        // Инициализация флага
        this.switchFileWriter = switchFileWriter;

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
            intWriter = new PrintWriter(new FileWriter(integers, this.switchFileWriter));
            stringWriter = new PrintWriter(new FileWriter(strings, this.switchFileWriter));
            floatWriter = new PrintWriter(new FileWriter(floats, this.switchFileWriter));
        } catch (IOException e) {
            // Обработка ошибок при работе с файлами
            throw new RuntimeException(e);
        }
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

    public void checkFile(){
        if (intCount == 0){
            getIntWriter().close();
            integers.delete();
        }
        if (stringCount == 0){
            getStringWriter().close();
            strings.delete();
        }
        if (floatCount == 0){
            getFloatWriter().close();
            floats.delete();
        }
    }

}
