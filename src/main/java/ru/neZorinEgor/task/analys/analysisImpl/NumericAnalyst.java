package ru.neZorinEgor.task.analys.analysisImpl;

import ru.neZorinEgor.task.analys.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumericAnalyst implements Analyst {
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

    public void doAnalysisAndGetStatistics(File file, boolean option){
        if (file.exists()){
            setSumm(0);
            setMin(10000);
            setMax(-10000);
            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
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
            System.out.println("Статистика для: " + file.getName());
            System.out.println("└─ Количество элементов: " + getLineCount());
            if (option){
                soutNumericResult();
            }
            setLineLength(0);
        } else {
            System.out.println("File not exist: " + file.getName());
        }
    }

    public void soutNumericResult(){
        System.out.println("\t└─ Дополнительные детали:\t");
        System.out.println("\t\t├── Минимум: " + getMin());
        System.out.println("\t\t├─ Максимум: " + getMax());
        System.out.println("\t\t├──── Сумма: " + summ);
        System.out.println("\t\t└── Среднее: " + summ / getLineCount());
        System.out.println();
    }
}
