package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.*;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) {
        FileManager fileManager = new FileManager(false);
        Analyst integerAnalyst = new NumericAnalyst(false);
        Analyst floatAnalyst = new NumericAnalyst(false);
        Analyst stringAnalyst = new StringAnalyst(false);

        System.out.print("Enter file path: ");
        Scanner fileName = new Scanner(System.in);
        File fileNameFromScanner = new File(fileName.nextLine());
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileNameFromScanner);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        try {
            while (scanner.hasNextLine()) {
                fileManager.doFilter(scanner.nextLine());
            }
            fileManager.checkFile();

            //опция для статистики простой | полной
            integerAnalyst.doAnalysis(fileManager.getIntegers());
            floatAnalyst.doAnalysis(fileManager.getFloats());
            stringAnalyst.doAnalysis(fileManager.getStrings());

            scanner.close();
        } catch (NullPointerException e) {
            System.out.println("Убедитесь в наименовании файла");
        }
    }
}