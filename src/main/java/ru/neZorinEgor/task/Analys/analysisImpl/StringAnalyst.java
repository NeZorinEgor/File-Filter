package ru.neZorinEgor.task.Analys.analysisImpl;

import ru.neZorinEgor.task.Analys.Analyst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringAnalyst implements Analyst {
    public StringAnalyst(boolean fullStatistic){
        this.fullStatistic = fullStatistic;

    }

    private boolean fullStatistic;

    private double min; //поле для перезаписи при сравнение
    private double max; //поле для перезаписи при сравнение
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
            // настройки для корректного присвоения при
            //                         первой итерации и корректного вывода информации в цикле
            //TODO починить счетчик, если изначально поступает пустой файл
            setMin(1000000000);
            setMax(0);
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
                if (lineValue.length() > getMax()){
                    setMax(lineValue.length());
                }
                if (lineValue.length() < getMin()){
                    setMin(lineValue.length());
                }
            }
            System.out.println("Статистика для: " + file.getName());
            System.out.println("└─ Количество элементов: " + getLineCount());
            if (fullStatistic){
                soutNumericResult();
            }
            //что бы в цикле корректно выводить колличество элементов
            setLineLength(0);

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
