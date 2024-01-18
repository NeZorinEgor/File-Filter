package ru.neZorinEgor.task.Analysts;

import java.io.File;

public interface Analyst {
    long getLineCount();
    void collectAnalysis(File file, boolean option);
}
