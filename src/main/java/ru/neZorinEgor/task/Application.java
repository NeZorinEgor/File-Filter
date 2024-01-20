package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //TODO OptionsCoordinator  - сущьность, которая
        // Инициализация объектов
        FileManager fileManager = new FileManager(false);
        Analyst integerAnalyst = new NumericAnalyst(true);
        Analyst floatAnalyst = new NumericAnalyst(true);
        Analyst stringAnalyst = new StringAnalyst(true);

        // Ввод пути к файлу
        System.out.print("Enter file path: ");

        Scanner readFiles = new Scanner(System.in);
        String files = readFiles.nextLine();
        String[] split = files.split("\\s+");
        for (String file : split){
            File fileNameFromScanner = new File(file);
            try(Scanner scanner = new Scanner(fileNameFromScanner)){
                while (scanner.hasNextLine()) {
                    fileManager.doFilter(scanner.nextLine());
                }
                fileManager.deleteFileIfEmpty();
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                System.exit(130);
            }
        }
        // Анализ и вывод результатов
        integerAnalyst.analysisAndPrint(fileManager.getIntegers());
        floatAnalyst.analysisAndPrint(fileManager.getFloats());
        stringAnalyst.analysisAndPrint(fileManager.getStrings());
    }
}