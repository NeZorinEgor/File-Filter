package ru.neZorinEgor.task.аnalys;

import java.io.File;

/**
 * Аналитик должен уметь собирать статистику файла,
 *                          который ему дают и должен делиться колличеством строк файл.
 * Это важно для компанента записи / перезаписи файла
 */
public interface Analyst {
    void collectAnalysis(File file, boolean option);
    long getLineLength();
}
