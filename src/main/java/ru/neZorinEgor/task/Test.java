package ru.neZorinEgor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        String[] splitOptions = option.split("\\s+");
        for (String optin : splitOptions){
            System.out.println(optin);
            }
        }
    }
