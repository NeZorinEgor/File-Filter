package ru.neZorinEgor.task.Analys.analysisImpl;

import ru.neZorinEgor.task.Analys.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Сущность, анализирующая строковые файлы.
 */
public class StringAnalyst implements Analyst {
    public StringAnalyst(boolean haveStatistic, boolean fullStatistic){
        this.haveStatistic = haveStatistic;
        this.fullStatistic = fullStatistic;
    }

    private final boolean haveStatistic;
    private final boolean fullStatistic;

    private double minimumLength;
    private double maximumLength;
    private long lineCount = 0;

    public long getCount() {
        return lineCount;
    }

    public long getLineCount() {
        return lineCount;
    }

    public void setLineLength(long count) {
        this.lineCount = count;
    }

    public double getMinimumLength() {
        return minimumLength;
    }

    public double getMaximumLength() {
        return maximumLength;
    }

    public void setMinimumLength(double minimumLength) {
        this.minimumLength = minimumLength;
    }

    public void setMaximumLength(double maximumLength) {
        this.maximumLength = maximumLength;
    }

    /**
     * Проходка по файлу и вывод статистики
     * @param file файл для анализа
     */
    public void analysisAndPrint(File file){
        if (file.exists()){
            // Первоначальные настройки для корректной итерации, что бы было с чем сравнить.
            setMinimumLength(10000000);
            setMaximumLength(-10000000);
            Scanner scanner;

            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                // подсчет строк
                setLineLength(getCount() + 1);
                String lineValue = String.valueOf(scanner.nextLine());
                if (lineValue.length() > getMaximumLength()){
                    setMaximumLength(lineValue.length());
                }
                if (lineValue.length() < getMinimumLength()){
                    setMinimumLength(lineValue.length());
                }
            }
            if(haveStatistic){
                System.out.println("Statistics for: " + file.getName());
                System.out.println("└─ Number of elements: " + getLineCount());
                if (fullStatistic){
                    printFullStringResult();
                }
            }
            //что бы в цикле корректно выводить количество элементов
            setLineLength(0);

        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }

    public void printFullStringResult(){
        System.out.println("\t└─ Additional details:\t");
        System.out.println("\t\t├─── Minimum length: " + getMinimumLength());
        System.out.println("\t\t└─── Maximum length: " + getMaximumLength());
        System.out.println();
    }
}
