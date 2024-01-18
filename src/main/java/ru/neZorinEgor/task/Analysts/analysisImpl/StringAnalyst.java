package ru.neZorinEgor.task.Analysts.analysisImpl;

import ru.neZorinEgor.task.Analysts.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringAnalyst implements Analyst {
    private double min = 10000000; //поле для перезаписи при сравнение
    private double max = -1000000; //поле для перезаписи при сравнение
    private long lineCount = 0;

    public long getCount() {
        return lineCount;
    }

    public long getLineCount() {
        return lineCount;
    }

    public void setCount(long count) {
        this.lineCount = count;
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

    public void collectAnalysis(File file, boolean option){
        if (file.exists()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (scanner.hasNextLine()){
                setCount(getCount() + 1);
                String lineValue = String.valueOf(scanner.nextLine());
                if (lineValue.length() > getMax()){
                    setMax(lineValue.length());
                }
                if (lineValue.length() < getMin()){
                    setMin(lineValue.length());
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
        System.out.println("\t\t├─────── min: " + getMin());
        System.out.println("\t\t└─────── max: " + getMax());
        System.out.println();
    }
}
