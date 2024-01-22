package ru.neZorinEgor.task;


import ru.neZorinEgor.task.Analys.Analyst;
import ru.neZorinEgor.task.Analys.analysisImpl.NumericAnalyst;
import ru.neZorinEgor.task.Analys.analysisImpl.StringAnalyst;
import ru.neZorinEgor.task.FileManager.FileManager;
import ru.neZorinEgor.task.OptionManager.OptionManager;

public class Application {
    public static void main(String[] args) {
        OptionManager optionManager = new OptionManager();

        optionManager.parseUserInput(args);

        //object  argument: Append line, path, prefix
        FileManager fileManager = new FileManager(optionManager.isAppendMode(), optionManager.getOutputPath(), optionManager.getFilePrefix());
        //object  argument: Full Statistic
        Analyst integerAnalyst = new NumericAnalyst(optionManager.isHaveStatistic(), optionManager.isFullStatistic());
        Analyst floatAnalyst = new NumericAnalyst(optionManager.isHaveStatistic(), optionManager.isFullStatistic());
        Analyst stringAnalyst = new StringAnalyst(optionManager.isHaveStatistic(), optionManager.isFullStatistic());

        for (String file : optionManager.getFiles()){
            fileManager.doFilterFile(file);
        }

        fileManager.deleteFileIfEmpty();

        integerAnalyst.analysisAndPrint(fileManager.getIntegersFile());
        floatAnalyst.analysisAndPrint(fileManager.getFloatsFile());
        stringAnalyst.analysisAndPrint(fileManager.getStringsFile());
    }
}