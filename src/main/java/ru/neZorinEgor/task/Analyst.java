package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Analyst {
    public static void main(String[] args) {
        Analyst analyst = new Analyst();
        analyst.fullStatistics(new File("integers.txt"),
                new File("floats.txt"),
                new File("strings.txt"), true);
    }

    public void fullStatistics(File intFile, File floatFile, File stringFile, boolean option) {
        collectAnalysisforIntAndFloat(intFile, option);
        collectAnalysisforIntAndFloat(floatFile, option);
        collectAnalysisForString(stringFile, option);
    }

    public void collectAnalysisforIntAndFloat(File file, boolean option){
        float min = 10000;
        float max = -10000;
        float summ = 0;
        int count = 0;
        if (file.exists()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                float lineValue = Float.parseFloat(scanner.nextLine());
                summ += lineValue;
                count++;
                if (lineValue > max){
                    max = lineValue;
                }
                if (lineValue < min){
                    min = lineValue;
                }
            }
            System.out.println("Статистика для: " + file.getName());
            System.out.println("└─ Количество элементов: " + count);
            if (option){
                System.out.println("\t└─ Дополнительные детали:\t");
                System.out.println("\t\t├── Минимум: " + min);
                System.out.println("\t\t├─ Максимум: " + max);
                System.out.println("\t\t├──── Сумма: " + summ);
                System.out.println("\t\t└── Среднее: " + summ / count);
                System.out.println();
            }
        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }

    public void collectAnalysisForString(File file, boolean option){
        int min = 10000000;
        int max = 0;
        String maxLine = "";
        String minLine = "";
        int count = 0;
        if (file.exists()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                count++;
                String lineValue = String.valueOf(scanner.nextLine());
                if (lineValue.length() > max){
                    max = lineValue.length();
                    maxLine = lineValue;
                }
                if (lineValue.length() < min){
                    min = lineValue.length();
                    minLine = lineValue;
                }
            }
            System.out.println("Статистика для: " + file.getName());
            System.out.println("└─ Количество элементов: " + count);
            if (option){
                System.out.println("\t└─ Дополнительные детали:\t");
                System.out.println("\t\t├─────── min: " + minLine);
                System.out.println("\t\t└─────── max: " + maxLine);
                System.out.println();
            }
        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }
}
