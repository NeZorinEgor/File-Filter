package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split("\\s+");
        for (String  fileName : split){
            File file = new File(fileName);
            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.append("Hello on ").append(file.getName());
                printWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
