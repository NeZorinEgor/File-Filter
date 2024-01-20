package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // Инициализация объектов
        FileManager fileManager = new FileManager(true);
        Analyst integerAnalyst = new NumericAnalyst(true);
        Analyst floatAnalyst = new NumericAnalyst(true);
        Analyst stringAnalyst = new StringAnalyst(true);

        // Ввод пути к файлу
        System.out.print("Enter file path: ");

        Scanner fileName = new Scanner(System.in);
        String fileNames = fileName.nextLine();
        String[] split = fileNames.split("\\s+");
        for (String file : split){
            File fileNameFromScanner = new File(file);
            try{
                Scanner scanner = new Scanner(fileNameFromScanner);
                while (scanner.hasNextLine()) {
                    fileManager.doFilter(scanner.nextLine());
                }
                fileManager.deleteFileIfEmpty();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        // Анализ и вывод результатов
        integerAnalyst.analysisAndPrint(fileManager.getIntegers());
        floatAnalyst.analysisAndPrint(fileManager.getFloats());
        stringAnalyst.analysisAndPrint(fileManager.getStrings());
    }
}