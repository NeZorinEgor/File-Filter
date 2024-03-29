package ru.neZorinEgor.task.OptionManager;


import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, которая обрабатывает
 *                   программные аргументы и передает информацию для поведения аналитика и файлового менеджера.
 */
public class OptionManager {

    private final StringBuilder outputPath = new StringBuilder("");
    private final StringBuilder filePrefix = new StringBuilder("");

    private boolean appendMode = false;
    private boolean fullStatistic = false;
    private boolean haveStatistic = false;

    private final List<String> files = new ArrayList<>();

    /**
     * Лист пути до файлов, полученных из аргументов.
     */
    public List<String> getFiles() {
        return files;
    }

    /**
     * Опция для выходного пути файла.
     */
    public String getOutputPath() {
        return outputPath.toString();
    }

    /**
     * Опция для префикса файла.
     */
    public String getFilePrefix() {
        return filePrefix.toString();
    }

    /**
     * Опция для записи / перезаписи в файл.
     */
    public boolean isAppendMode() {
        return appendMode;
    }

    /**
     * Опция для отображения полной / кратной статистики.
     */
    public boolean isFullStatistic() {
        return fullStatistic;
    }

    /**
     * Опция для отображения статистики.
     */
    public boolean isHaveStatistic() {
        return haveStatistic;
    }

    /**
     * Парсит программные аргументы,
     *                   на основании которых передает флаги аналитику и файловому менеджеру.
     * @param options программные аргументы main(String[] args)
     */
    public void parseProgramArguments(String[] options){
        for (int i = 0; i < options.length; i++){
            if ("-o".equals(options[i])) {
                outputPath.append(options[i + 1]);
            } else if ("-p".equals(options[i])) {
                filePrefix.append(options[i + 1]);
            } else if ("-a".equals(options[i])) {
                appendMode = true;
            } else if ("-s".equals(options[i])) {
                haveStatistic = true;
                fullStatistic = false;
            } else if ("-f".equals(options[i])) {
                haveStatistic = true;
                fullStatistic = true;
            } else if (options[i].endsWith(".txt")) {
                files.add(options[i]);
            } else if (options[i-1].equals("-p")  || options[i-1].equals("-o")) {
                continue;
            } else {
                System.out.println();
                System.out.println(" >>> Error!!! Unknown option: " + options[i]);
                System.out.println("┌────────────────────────────   Message:   ───────────────────────────┐");
                System.out.println("| Please make sure you have entered the correct options and try again |");
                System.out.println("└─────────────────────────────────────────────────────────────────────┘");

                System.exit(1);
            }
        }
    }
}
