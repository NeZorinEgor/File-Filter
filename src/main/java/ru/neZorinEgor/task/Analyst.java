package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyst {
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

    public long getCount() {
        return count;
    }

    public long getLineCount() {
        return count;
    }

    public void setCount(long count) {
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

    public void collectAnalysisforIntAndFloat(File file, boolean option){
        if (file.exists()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                float lineValue = Float.parseFloat(scanner.nextLine());
                setSumm(getSumm()+ lineValue);
                setCount(getLineCount() + 1);
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
