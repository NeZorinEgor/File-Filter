package ru.neZorinEgor.task.Analys.analysisImpl;

import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class NumericAnalyst implements Analyst {
    public NumericAnalyst(boolean fullStatistic){
        this.fullStatistic = fullStatistic;

    }

    private boolean fullStatistic;

    private double min = 10000000; //поле для перезаписи при сравнение
    private double max = -1000000; //поле для перезаписи при сравнение
    private double summ = 0;
    private long count = 0;

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public long getLineCount() {
        return count;
    }

    public void setLineLength(long count) {
        this.count = count;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void analysisAndPrint(File file){
        if (file.exists()){
            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                //ошибка не выкенится, она обработается в точке входа приложения
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                float lineValue = Float.parseFloat(scanner.nextLine());
                setSumm(getSumm()+ lineValue);
                setLineLength(getLineCount() + 1);
                if (lineValue > getMax()){
                    setMax(lineValue);
                }
                if (lineValue < getMin()){
                    setMin(lineValue);
                }
            }
            System.out.println("Statistics for: " + file.getName());
            System.out.println("└─ Number of elements: " + getLineCount());
            if (fullStatistic){
                soutNumericResult();
            }
            setLineLength(0);
        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }

    public void soutNumericResult(){
        System.out.println("\t└─ Additional details:\t");
        System.out.println("\t\t├── Minimum: " + getMin());
        System.out.println("\t\t├── Maximum: " + getMax());
        System.out.println("\t\t├─── Amount: " + summ);
        System.out.println("\t\t└── Average: " + summ / getLineCount());
        System.out.println();
    }
}
