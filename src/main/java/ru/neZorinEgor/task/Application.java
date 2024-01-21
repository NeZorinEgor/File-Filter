package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //object  argument: Append Line
        FileManager fileManager = new FileManager(false);
        //object  argument: Full Statistic
        Analyst integerAnalyst = new NumericAnalyst(true);
        Analyst floatAnalyst = new NumericAnalyst(true);
        Analyst stringAnalyst = new StringAnalyst(true);

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
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found: " + e.getMessage());
                System.out.println("Please make sure you entered the correct file name and try again.");
                System.exit(130);
            }
        }
        fileManager.deleteFileIfEmpty();

        integerAnalyst.analysisAndPrint(fileManager.getIntegersFile());
        floatAnalyst.analysisAndPrint(fileManager.getFloatsFile());
        stringAnalyst.analysisAndPrint(fileManager.getStringsFile());
    }
}