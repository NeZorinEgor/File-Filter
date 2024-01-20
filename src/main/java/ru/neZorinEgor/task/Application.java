package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.*;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) {
        while (true){
            // Инициализация объектов
            FileManager fileManager = new FileManager(false);
            Analyst integerAnalyst = new NumericAnalyst(true);
            Analyst floatAnalyst = new NumericAnalyst(true);
            Analyst stringAnalyst = new StringAnalyst(true);

            // Ввод пути к файлу
            System.out.print("Enter file path: ");
            Scanner fileName = new Scanner(System.in);
            File fileNameFromScanner = new File(fileName.nextLine());

            try (Scanner scanner = new Scanner(fileNameFromScanner)) {
                while (scanner.hasNextLine()) {
                    fileManager.doFilter(scanner.nextLine());
                }
                fileManager.deleteFileIfEmpty();

                // Анализ и вывод результатов
                integerAnalyst.analysisAndPrint(fileManager.getIntegers());
                floatAnalyst.analysisAndPrint(fileManager.getFloats());
                stringAnalyst.analysisAndPrint(fileManager.getStrings());
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            }
        }
    }
}