package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;
import ru.neZorinEgor.task.OptionManager.OptionManager;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        OptionManager optionManager = new OptionManager();

        optionManager.getUserInput();

        //object  argument: Append line, path, prefix
        FileManager fileManager = new FileManager(optionManager.isAppendMode(), optionManager.getPath(), optionManager.getPrefix());
        //object  argument: Full Statistic
        Analyst integerAnalyst = new NumericAnalyst(optionManager.isFullStatistic());
        Analyst floatAnalyst = new NumericAnalyst(optionManager.isFullStatistic());
        Analyst stringAnalyst = new StringAnalyst(optionManager.isFullStatistic());

        for (String file : optionManager.getFiles()){
            fileManager.doFilterFile(new File(file));
        }
        fileManager.deleteFileIfEmpty();

        integerAnalyst.analysisAndPrint(fileManager.getIntegersFile());
        floatAnalyst.analysisAndPrint(fileManager.getFloatsFile());
        stringAnalyst.analysisAndPrint(fileManager.getStringsFile());
    }
}