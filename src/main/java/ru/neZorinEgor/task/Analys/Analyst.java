package ru.neZorinEgor.task.Analys;

import java.io.File;

/**
 * Аналитик должен уметь анализировать
 *                        файла, который ему дают
 */
public interface Analyst {
    /**
     *
     * @param file - файл для анализа
     */
    void analysisAndPrint(File file);
}
