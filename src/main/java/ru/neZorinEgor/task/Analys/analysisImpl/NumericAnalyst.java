package ru.neZorinEgor.task.Analys.analysisImpl;

import ru.neZorinEgor.task.Analys.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Сущность, анализирующая целочисленные и с плавающей точкой файлы.
 */
public class NumericAnalyst implements Analyst {
    public NumericAnalyst(boolean haveStatistic, boolean fullStatistic){
        this.haveStatistic = haveStatistic;
        this.fullStatistic = fullStatistic;
    }

    private final boolean fullStatistic;
    private final boolean haveStatistic;

    //Поля для перезаписи при сравнении
    private double minimum = 10000000;
    private double maximum = -1000000;
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

    /**
     * Проходка по файлу и вывод статистики
     * @param file файл для анализа
     */
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

    //Полная статистика.
    public void printFullNumericStatistic() {
        DecimalFormat decimalFormat = new DecimalFormat("#.################"); // Задайте нужный формат для float числа

        System.out.println("\t└─ Additional details:\t");
        System.out.println("\t\t├─── Minimum: " + decimalFormat.format(getMinimum()));
        System.out.println("\t\t├─── Maximum: " + decimalFormat.format(getMaximum()));
        System.out.println("\t\t├──── Amount: " + decimalFormat.format(amount));
        System.out.println("\t\t└─── Average: " + decimalFormat.format(amount / getLineCount()));
        System.out.println();
    }
}
