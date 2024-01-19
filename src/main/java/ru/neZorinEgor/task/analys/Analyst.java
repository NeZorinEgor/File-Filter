package ru.neZorinEgor.task.analys;

import java.io.File;

/**
 * Аналитик должен уметь собирать статистику файла,
 *                          который ему дают и должен делиться количеством строк файл.
 * Это это поможет компаненту записи / перезаписи файла.
 */
public interface Analyst {
    /**
     *
     * @param file - файл для анализа
     * @param option - true полная статистика / false коротка
     */
    void doAnalysisAndGetStatistics(File file, boolean option);

    /**
     * @return Количество строк в файле
     */
    long getLineCount();
}
