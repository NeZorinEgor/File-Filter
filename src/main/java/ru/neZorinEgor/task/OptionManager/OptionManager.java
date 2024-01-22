package ru.neZorinEgor.task.OptionManager;

import ru.neZorinEgor.task.FileManager.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionManager {
    private final StringBuilder path = new StringBuilder("");
    private final StringBuilder prefix = new StringBuilder("");
    private boolean appendMode = false;
    private boolean fullStatistic = false;
    private final List<String> files = new ArrayList<>();

    public List<String> getFiles() {
        return files;
    }

    public String getPath() {
        return path.toString();
    }

    public String getPrefix() {
        return prefix.toString();
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public boolean isFullStatistic() {
        return fullStatistic;
    }

    public void getUserInput(){
        Scanner scanFiles = new Scanner(System.in);
        String files = scanFiles.nextLine();
        String[] options = files.split("\\s+");
        parseUserInput(options);
    }

    private void parseUserInput(String[] options){
        for (int i = 0; i < options.length; i++){
            if ("-o".equals(options[i])) {
                path.append(options[i + 1]);
            } else if ("-p".equals(options[i])) {
                prefix.append(options[i + 1]);
            } else if ("-a".equals(options[i])) {
                appendMode = true;
            } else if ("-s".equals(options[i])) {
                fullStatistic = false;
            } else if ("-f".equals(options[i])) {
                fullStatistic = true;
            } else if (options[i].matches(".*.txt$")) {
                files.add(options[i]);
            }
            else if (options[i-1].equals("-p")  || options[i-1].equals("-o")) {
                //checkPrefix(split[i]) || checkPath(split[i])
                // if (checkPrefix(split[i])){
                //     continue;
                //{ else throw new Exeption;
                continue;
            }else {
                System.out.println("неизвестная опция:" + options[i]);
            }
        }
    }
}
