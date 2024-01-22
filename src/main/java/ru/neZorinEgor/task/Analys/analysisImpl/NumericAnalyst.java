package ru.neZorinEgor.task.Analys.analysisImpl;

import ru.neZorinEgor.task.Analys.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumericAnalyst implements Analyst {
    public NumericAnalyst(boolean haveStatistic, boolean fullStatistic){
        this.haveStatistic = haveStatistic;
        this.fullStatistic = fullStatistic;
    }

    private final boolean fullStatistic;
    private final boolean haveStatistic;

    private double minimum = 10000000; //поле для перезаписи при сравнении
    private double maximum = -1000000; //поле для перезаписи при сравнении
    private double amount = 0;
    private long count = 0;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getLineCount() {
        return count;
    }

    public void setLineLength(long count) {
        this.count = count;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public void analysisAndPrint(File file){
        if (file.exists()){
            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                //ошибка не выкинется, она обработается в точке входа приложения
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                float lineValue = Float.parseFloat(scanner.nextLine());
                setAmount(getAmount()+ lineValue);
                setLineLength(getLineCount() + 1);
                if (lineValue > getMaximum()){
                    setMaximum(lineValue);
                }
                if (lineValue < getMinimum()){
                    setMinimum(lineValue);
                }
            }
            if (haveStatistic){
                System.out.println("Statistics for: " + file.getName());
                System.out.println("└─ Number of elements: " + getLineCount());
                if (fullStatistic){
                    printFullNumericStatistic();
                }
            }

            setLineLength(0);
        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }

    public void printFullNumericStatistic(){
        System.out.println("\t└─ Additional details:\t");
        System.out.println("\t\t├─── Minimum: " + getMinimum());
        System.out.println("\t\t├─── Maximum: " + getMaximum());
        System.out.println("\t\t├──── Amount: " + amount);
        System.out.println("\t\t└─── Average: " + amount / getLineCount());
        System.out.println();
    }
}
